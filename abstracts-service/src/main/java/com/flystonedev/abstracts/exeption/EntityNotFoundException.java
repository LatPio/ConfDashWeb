package com.flystonedev.abstracts.exeption;


import com.flystonedev.abstracts.exeption.config.GlobalErrorCode;

public class EntityNotFoundException extends ErrorWrapper{

    public EntityNotFoundException() {
        super("Exception Entity not found", GlobalErrorCode.ERROR_ABSTRACT_SERVICE_ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(String message, Long code) {
        super(message, code);
    }
}
