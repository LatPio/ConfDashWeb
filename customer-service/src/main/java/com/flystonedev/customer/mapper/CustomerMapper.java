package com.flystonedev.customer.mapper;

import com.flystonedev.customer.DTO.CustomerDTO;
import com.flystonedev.customer.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerDTO map(Customer customer);

    Customer map(CustomerDTO customerDTO);

}
