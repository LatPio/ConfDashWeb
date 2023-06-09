package com.flystonedev.customer.exeption;

import com.flystonedev.customer.exeption.config.GlobalErrorCode;

public class EntityNotFoundException extends ErrorWrapper{

    public EntityNotFoundException() {
        super("Exception Entity not found", GlobalErrorCode.ERROR_CUSTOMER_SERVICE_ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(String message, Long code) {
        super(message, code);
    }
}
