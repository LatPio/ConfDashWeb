package com.flystonedev.customer.mapper;

import com.flystonedev.customer.DTO.CustomerCardDTO;
import com.flystonedev.customer.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerCardMapper {

    CustomerCardDTO map(Customer customer);

    Customer mpa(CustomerCardDTO customerCardDTO);

}
