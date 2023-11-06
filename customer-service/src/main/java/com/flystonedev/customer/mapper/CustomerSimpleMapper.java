package com.flystonedev.customer.mapper;

import com.flystonedev.customer.DTO.CustomerDTO;
import com.flystonedev.customer.DTO.CustomerIdDTO;
import com.flystonedev.customer.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerSimpleMapper {

    CustomerIdDTO map(Customer customer);

    Customer map(CustomerIdDTO customerIdDTO);
}
