package com.flystonedev.cutomer.mapper;


import com.flystonedev.cutomer.model.Customer;
import com.flystonedev.cutomer.records.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // Aby zadziałało po restarcjie usuń adnotacje zrestrartuj clean build uruchom i wtedy po błedzie usuń tu komentrzra przywaracjąć adnotacjie  wtedy build i powinnien dobrze zrobnić
public abstract class CustomerMapper {

    public abstract CustomerResponse map(Customer customer);

    public abstract Customer map(CustomerResponse customerResponse);

    public abstract List<CustomerResponse> mapToRecord(List<Customer> customerList);



}
