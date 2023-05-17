package com.flystonedev.cutomer.service;


import com.flystonedev.cutomer.DTO.CustomerDTO;
import com.flystonedev.cutomer.mapper.CustomerMapper;
import com.flystonedev.cutomer.model.Customer;
import com.flystonedev.cutomer.DTO.CustomerRegistrationRequest;
import com.flystonedev.cutomer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);


    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: validation email is vaild
        //todo: validation email not taken

        customerRepository.save(customer);
    }

    public List<CustomerDTO> listOfAllCustomers(){
        List<Customer> customersList = customerRepository.findAll();
        return customersList.stream().map(customerMapper::map).collect(Collectors.toList());
    }
    public CustomerDTO get(Integer id){
        return customerRepository.findById(id).map(customerMapper::map).orElse(null);
    }
    public CustomerDTO update(CustomerDTO customerDTO){
        CustomerDTO exist = get(customerDTO.getId());
        if (exist == null) {
            return null;
        }
        Customer updated = customerRepository.save(customerMapper.map(customerDTO));
        return customerMapper.map(updated);
    }
    public void delete(Integer id){
        customerRepository.deleteById(id);
    }

}
