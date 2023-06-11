package com.flystonedev.cutomer.mapper;


import com.flystonedev.cutomer.DTO.CustomerAdminDTO;
import com.flystonedev.cutomer.model.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerAdminMapper {


    CustomerAdminDTO map(Customer customer);

    Customer map(CustomerAdminDTO customerAdminDTO);





}
