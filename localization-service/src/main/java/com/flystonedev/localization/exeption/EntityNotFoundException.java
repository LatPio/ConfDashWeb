package com.flystonedev.localization.exeption;


import com.flystonedev.localization.exeption.config.GlobalErrorCode;

public class EntityNotFoundException extends ErrorWrapper {

    public EntityNotFoundException() {
        super("Exception Entity not found", GlobalErrorCode.ERROR_LOCALIZATION_SERVICE_ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(String message, Long code) {
        super(message, code);
    }
}
