package com.flystonedev.cutomer.mapper;


import com.flystonedev.cutomer.DTO.CustomerDTO;
import com.flystonedev.cutomer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerDTO map(Customer customer);

    Customer map(CustomerDTO customerDTO);





}
