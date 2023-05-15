package com.flystonedev.cutomer.mapper;


import com.flystonedev.cutomer.model.Customer;
import com.flystonedev.cutomer.records.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse map(Customer customer);

    Customer map(CustomerResponse customerResponse);

    List<CustomerResponse> mapToRecord(List<Customer> customerList);



}
