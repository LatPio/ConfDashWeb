package com.flystonedev.cutomer.mapper;

import com.flystonedev.cutomer.DTO.CustomerCardDTO;
import com.flystonedev.cutomer.DTO.CustomerDTO;
import com.flystonedev.cutomer.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerCardMapper {

    CustomerCardDTO map(Customer customer);

    Customer mpa(CustomerCardDTO customerCardDTO);

}
