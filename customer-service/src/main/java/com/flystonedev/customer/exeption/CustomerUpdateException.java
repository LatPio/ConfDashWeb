package com.flystonedev.customer.exeption;

import com.flystonedev.customer.exeption.config.GlobalErrorCode;

public class CustomerUpdateException extends ErrorWrapper{

    public CustomerUpdateException() {
        super("Customer can by edited by it's creator", GlobalErrorCode.ERROR_CUSTOMER_SERVICE_ENTITY_NOT_FOUND);
    }
    public CustomerUpdateException(String message, Long code) {
        super(message, code);
    }
}
