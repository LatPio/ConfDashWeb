package com.flystonedev.event.exeption;


import com.flystonedev.event.exeption.config.GlobalErrorCode;

public class EntityNotFoundException extends ErrorWrapper {

    public EntityNotFoundException() {
        super("Exception Entity not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(String message, Long code) {
        super(message, code);
    }
}
