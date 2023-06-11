package com.flystonedev.cutomer.exeption;

import com.flystonedev.cutomer.exeption.config.GlobalErrorCode;

public class CustomerUpdateException extends ErrorWrapper{

    public CustomerUpdateException() {
        super("Customer can by edited by its creator", GlobalErrorCode.ERROR_CUSTOMER_SERVICE_ENTITY_NOT_FOUND);
    }
    public CustomerUpdateException(String message, Long code) {
        super(message, code);
    }
}
