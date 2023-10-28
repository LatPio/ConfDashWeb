package com.flystonedev.customer.mapper;

import com.flystonedev.customer.DTO.InvoiceDataDTO;
import com.flystonedev.customer.model.InvoiceData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceDataMapper {

  InvoiceDataDTO map(InvoiceData invoiceData);

  InvoiceData map(InvoiceDataDTO departmentResponse);
}
