package com.flystonedev.cutomer.service;


import com.flystonedev.cutomer.DTO.CustomerDTO;
import com.flystonedev.cutomer.exeption.EntityNotFoundException;
import com.flystonedev.cutomer.exeption.UserAlreadyRegisteredException;
import com.flystonedev.cutomer.exeption.config.GlobalErrorCode;
import com.flystonedev.cutomer.mapper.CustomerMapper;
import com.flystonedev.cutomer.model.Customer;
import com.flystonedev.cutomer.DTO.CustomerRegistrationRequest;
import com.flystonedev.cutomer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    private final KeycloakUserManagementService keycloakUserManagementService;


    public void registerCustomer(CustomerRegistrationRequest request) throws Exception  {

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
                log.info("User created in keycloak {} ", request.email());
                Customer customer = Customer.builder()
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .email(request.email())
                        .authID(userRepresentation.getId())
                        .build();
                customerRepository.save(customer);
            }
        }
    }

    public List<CustomerDTO> listOfAllCustomers(){
        List<Customer> customersList = customerRepository.findAll();
        return customersList.stream().map(customerMapper::map).collect(Collectors.toList());
    }
    public CustomerDTO get(Integer id){
        return customerRepository.findById(id).map(customerMapper::map).orElseThrow(EntityNotFoundException::new);
    }
    public CustomerDTO update(CustomerDTO customerDTO){
        Customer exist = customerMapper.map(get(customerDTO.getId()));
        if (exist == null) {
            return null;
        }

        UserRepresentation userRepresentation = keycloakUserManagementService.readUser(exist.getAuthID());
        userRepresentation.setLastName(customerDTO.getLastName());
        userRepresentation.setFirstName(customerDTO.getFirstName());
        keycloakUserManagementService.updateUser(userRepresentation);

        Customer updated = customerRepository.save(customerMapper.map(customerDTO));
        return customerMapper.map(updated);
    }
    public void delete(Integer id){
        customerRepository.deleteById(id);
    }

}
