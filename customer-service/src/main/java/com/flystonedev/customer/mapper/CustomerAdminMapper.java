package com.flystonedev.customer.mapper;


import com.flystonedev.customer.DTO.CustomerAdminDTO;
import com.flystonedev.customer.model.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerAdminMapper {


    CustomerAdminDTO map(Customer customer);

    Customer map(CustomerAdminDTO customerAdminDTO);





}
