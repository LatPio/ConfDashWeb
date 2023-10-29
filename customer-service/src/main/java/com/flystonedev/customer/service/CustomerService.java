package com.flystonedev.customer.service;


import com.flystonedev.customer.DTO.*;
import com.flystonedev.customer.config.JwtConverter;
import com.flystonedev.customer.exeption.CustomerUpdateException;
import com.flystonedev.customer.exeption.EntityNotFoundException;
import com.flystonedev.customer.exeption.UserAlreadyRegisteredException;
import com.flystonedev.customer.exeption.config.GlobalErrorCode;
import com.flystonedev.customer.mapper.CustomerAdminMapper;
import com.flystonedev.customer.mapper.CustomerCardMapper;
import com.flystonedev.customer.mapper.CustomerMapper;
import com.flystonedev.customer.model.Customer;
import com.flystonedev.customer.repository.CustomerRepository;
import com.flystonedev.customer.repository.InvoiceDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final InvoiceDataRepository invoiceDataRepository;
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    private final CustomerCardMapper customerCardMapper = Mappers.getMapper(CustomerCardMapper.class);
    private final CustomerAdminMapper customerAdminMapper = Mappers.getMapper(CustomerAdminMapper.class);
    private final JwtConverter jwtConverter;

    private final KeycloakUserManagementService keycloakUserManagementService;


    public void registerCustomer(CustomerRegistrationRequest request) {

        List<UserRepresentation> userRepresentationList = keycloakUserManagementService.readUserByEmail(request.email());

        if(!userRepresentationList.isEmpty()){
            throw new UserAlreadyRegisteredException("This email already registered in our database. Please check and retry", GlobalErrorCode.ERROR_CUSTOMER_SERVICE_USER_ALREADY_REGISTERED);
        } else {
            //todo Integrate notification to user email for now user is authenticared create endpoint for activation

            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setEmail(request.email());
            userRepresentation.setEmailVerified(true);
            userRepresentation.setEnabled(true);
            userRepresentation.setUsername(request.email());
            userRepresentation.setFirstName(request.firstName());
            userRepresentation.setLastName(request.lastName());

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setValue(request.password());
            credentialRepresentation.setTemporary(false);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

            Integer userCreationResponse = keycloakUserManagementService.createUser(userRepresentation);

            if (userCreationResponse == 201){
                Optional<UserRepresentation> retriedUser = keycloakUserManagementService.readUserByEmail(request.email()).stream().findFirst();
                String userId = retriedUser.get().getId();
                log.info("User successfully created in keycloak, provided with email: {} ", request.email());
                Customer customer = Customer.builder()
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .email(request.email())
                        .authID(userId)
                        .build();
                customerRepository.save(customer);
            }
        }
    }

    /*
     *
     * !!!!!!!!! USER SERVICE METHODS !!!!!!!!
     *
     * */
    public CustomerCardDTO getUserSimple(Integer id){
        return customerRepository.findById(id).map(customerCardMapper::map).orElseThrow(EntityNotFoundException::new);
    }
    public CustomerDTO getUser(Integer id){
        return customerRepository.findCustomerByIdAndAuthID(id, jwtConverter.getKeycloakUserID()).map(customerMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public CustomerDTO getUserByAuthId(){
        return customerRepository.findCustomerByAuthID(jwtConverter.getKeycloakUserID()).map(customerMapper::map).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional

    public CustomerDTO updateUser(CustomerDTO customerDTO){
        Customer exist = customerMapper.map(getUser(customerDTO.getId()));
        if (exist == null) {
            throw new EntityNotFoundException("User not found", GlobalErrorCode.ERROR_CUSTOMER_SERVICE_ENTITY_NOT_FOUND);
        } else
            if(!Objects.equals(exist.getAuthID(), jwtConverter.getKeycloakUserID())){
            throw new CustomerUpdateException("You can edit only yours Customer!", GlobalErrorCode.ERROR_CUSTOMER_SERVICE_UPDATE_BLOCKED);
        } else {
            UserRepresentation userRepresentation = keycloakUserManagementService.readUser(exist.getAuthID());
            userRepresentation.setLastName(customerDTO.getLastName());
            userRepresentation.setFirstName(customerDTO.getFirstName());
            keycloakUserManagementService.updateUser(userRepresentation);

            Customer updated = customerRepository.save(customerMapper.map(customerDTO));
            return customerMapper.map(updated);
        }
    }
    public List<CustomerCardDTO> listOfAllCustomersCards(){
        List<Customer> customersList = customerRepository.findAll();
        return customersList.stream().map(customerCardMapper::map).collect(Collectors.toList());
    }

    /*
     *
     * !!!!!!!!! ADMIN SERVICE METHODS !!!!!!!!
     *
     * */

    public List<CustomerAdminDTO> listOfAllCustomersForAdmin(){
        List<Customer> customersList = customerRepository.findAll();
        return customersList.stream().map(customerAdminMapper::map).collect(Collectors.toList());
    }
    public CustomerAdminDTO getAdmin(Integer id){
        return customerRepository.findById(id).map(customerAdminMapper::map).orElseThrow(EntityNotFoundException::new);
    }
    public CustomerAdminDTO updateAdmin(CustomerAdminDTO customerAdminDTO){
        Customer exist = customerAdminMapper.map(getAdmin(customerAdminDTO.getId()));
        if (exist == null) {
            throw new EntityNotFoundException("User not found", GlobalErrorCode.ERROR_CUSTOMER_SERVICE_ENTITY_NOT_FOUND);
        }

        UserRepresentation userRepresentation = keycloakUserManagementService.readUser(exist.getAuthID());
        userRepresentation.setLastName(customerAdminDTO.getLastName());
        userRepresentation.setFirstName(customerAdminDTO.getFirstName());
        keycloakUserManagementService.updateUser(userRepresentation);

        Customer updated = customerRepository.save(customerAdminMapper.map(customerAdminDTO));
        return customerAdminMapper.map(updated);
    }
    @Transactional
    public void delete(Integer id){
        String authID = customerRepository.getReferenceById(id).getAuthID();
        keycloakUserManagementService.deleteUser(authID);
        customerRepository.deleteById(id);
    }

    public StatsResponse stats() {
        return new StatsResponse(
                customerRepository.count(),
                invoiceDataRepository.count(),
                invoiceDataRepository.countDistinctInstitutions(),
                invoiceDataRepository.countDistinctCountries()
        );
    }
}
