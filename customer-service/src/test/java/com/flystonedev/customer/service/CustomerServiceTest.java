package com.flystonedev.customer.service;

import com.flystonedev.customer.DTO.CustomerRegistrationRequest;
import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.config.JwtConverter;
import com.flystonedev.customer.exeption.EntityNotFoundException;
import com.flystonedev.customer.exeption.UserAlreadyRegisteredException;
import com.flystonedev.customer.mapper.CustomerAdminMapper;
import com.flystonedev.customer.mapper.CustomerCardMapper;
import com.flystonedev.customer.mapper.CustomerMapper;
import com.flystonedev.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest implements SampleData {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private KeycloakUserManagementService keycloakUserManagementService;
    @Mock
    private JwtConverter jwtConverter;
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    private final CustomerCardMapper customerCardMapper = Mappers.getMapper(CustomerCardMapper.class);
    private final CustomerAdminMapper customerAdminMapper = Mappers.getMapper(CustomerAdminMapper.class);

    @Test
    void canAdminOrUserRegisterNewCustomer() {
        //given
        CustomerRegistrationRequest request = getSampleOfCustomerRegistrationRequest();
        when(keycloakUserManagementService.readUserByEmail(request.email())).thenReturn(new ArrayList<>());
        when(keycloakUserManagementService.createUser(any())).thenReturn(201);
        //when
        customerService.registerCustomer(request);
        //then

        verify(customerRepository, times(1)).save(getSampleOfCustomerAtRegistration());
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void willThrowErrorWhenEmailIsInDatabaseWhileRegistrationOfCustomer(){
        CustomerRegistrationRequest request = getSampleOfCustomerRegistrationRequest();
        when(keycloakUserManagementService.readUserByEmail(request.email())).thenReturn(Arrays.asList(getSampleOfCustomerRepresentation()));
        //when
        //then
        assertThatThrownBy(() -> customerService.registerCustomer(request))
                .isInstanceOf(UserAlreadyRegisteredException.class)
                .hasMessageContaining("This email already registered in our database. Please check and retry");
    }

    @Test
    void canUserGetSimpleRepresentationOfCustomer() {
        var excepted = getSampleOfCustomerCardDTO();
        var repositoryCall = getSampleOfCustomers().get(1);
        when(customerRepository.findById(excepted.getId())).thenReturn(Optional.ofNullable(repositoryCall));

        var actual  = customerService.getUserSimple(excepted.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(customerRepository, times(1)).findById(excepted.getId());
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void willThrowErrorWhenUserGetSimpleCardOfCustomer(){
        when(customerRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> customerService.getUserSimple(anyInt()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");

    }

    @Test
    void canUserGetOwnCustomerInformation() {
        var excepted = getSampleOfCustomerDTO();
        var repositoryCall = getSampleOfCustomers().get(1);
        when(jwtConverter.getKeycloakUserID()).thenReturn(repositoryCall.getAuthID());

        when(customerRepository.findCustomerByIdAndAuthID(repositoryCall.getId(),repositoryCall.getAuthID())).thenReturn(Optional.ofNullable(repositoryCall));

        var actual  = customerService.getUser(excepted.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(customerRepository, times(1)).findCustomerByIdAndAuthID(repositoryCall.getId(),repositoryCall.getAuthID());
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void willThrowErrorWhenUserGetOwnCustomerInformation(){
        String authId = "aaaa";
        Integer id = 10;
        when(jwtConverter.getKeycloakUserID()).thenReturn(authId);
        when(customerRepository.findCustomerByIdAndAuthID(eq(id),eq(authId))).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> customerService.getUser(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");

    }
    @Test
    void canUserUpdateOwnCustomerInformation() {
        //given
        var request = getSampleOfCustomerDTO();
        var repositoryCall = getSampleOfCustomers().get(1);
        when(jwtConverter.getKeycloakUserID()).thenReturn(repositoryCall.getAuthID());
        when(keycloakUserManagementService.readUser(repositoryCall.getAuthID())).thenReturn(getSampleOfCustomerRepresentation());
        when(customerRepository.findCustomerByIdAndAuthID(repositoryCall.getId(),repositoryCall.getAuthID())).thenReturn(Optional.ofNullable(repositoryCall));
        //when
        var actual = customerService.updateUser(request);
        //then

        verify(customerRepository, times(1)).save(customerMapper.map(request));
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void willThrowErrorWhenUserUpdateOwnCustomerInformation() {
        //given
        var request = getSampleOfCustomerDTO();
        var repositoryCall = getSampleOfCustomers().get(1);
        when(jwtConverter.getKeycloakUserID()).thenReturn(repositoryCall.getAuthID());
        when(customerRepository.findCustomerByIdAndAuthID(repositoryCall.getId(),repositoryCall.getAuthID())).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> customerService.updateUser(request))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");
    }

    @Test
    void canUserGetListOfAllCustomersCards() {

        customerService.listOfAllCustomersCards();

        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void canAdminGetListOfAllCustomersInformation() {

        customerService.listOfAllCustomersForAdmin();
        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);

    }

    @Test
    void canAdminGetOfAnyCustomerInformation() {
        var excepted = getSampleOfCustomerAdminDTO();
        var repositoryCall = getSampleOfCustomers().get(1);

        when(customerRepository.findById(repositoryCall.getId())).thenReturn(Optional.ofNullable(repositoryCall));

        var actual  = customerService.getAdmin(excepted.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(customerRepository, times(1)).findById(repositoryCall.getId());
        verifyNoMoreInteractions(customerRepository);
    }
    @Test
    void willThrowErrorWhenAdminGetCustomer(){
        when(customerRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> customerService.getAdmin(anyInt()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");

    }

    @Test
    void canAdminUpdateOfAnyCustomerInformation() {
        //given
        var request = getSampleOfCustomerAdminDTO();
        var repositoryCall = getSampleOfCustomers().get(1);
        when(keycloakUserManagementService.readUser(repositoryCall.getAuthID())).thenReturn(getSampleOfCustomerRepresentation());
        when(customerRepository.findById(repositoryCall.getId())).thenReturn(Optional.ofNullable(repositoryCall));
        //when
        var actual = customerService.updateAdmin(request);
        //then

        verify(customerRepository, times(1)).save(customerAdminMapper.map(request));
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void canUserDeleteCustomer() {
        var repositoryCall = getSampleOfCustomers().get(1);
        when(customerRepository.getReferenceById(anyInt())).thenReturn(repositoryCall);
        doNothing().when(customerRepository).deleteById(anyInt());
        doNothing().when(keycloakUserManagementService).deleteUser(repositoryCall.getAuthID());

        customerService.delete(1);
        verify(customerRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(customerRepository);


    }
}