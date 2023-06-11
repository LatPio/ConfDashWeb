package com.flystonedev.cutomer.exeption.config;

import com.flystonedev.cutomer.exeption.ErrorWrapper;

public class EntityAlreadyInDatabaseException extends ErrorWrapper {

    public EntityAlreadyInDatabaseException() {
        super("Exception Entity not found", GlobalErrorCode.ERROR_CUSTOMER_SERVICE_ENTITY_ALREADY_IN_DATABASE);
    }
    public EntityAlreadyInDatabaseException(String message, Long code) {
        super(message, code);
    }
}
