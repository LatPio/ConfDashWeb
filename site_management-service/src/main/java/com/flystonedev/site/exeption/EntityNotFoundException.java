package com.flystonedev.site.exeption;


import com.flystonedev.site.exeption.config.GlobalErrorCode;

public class EntityNotFoundException extends ErrorWrapper {

    public EntityNotFoundException() {
        super("Exception Entity not found", GlobalErrorCode.ERROR_SITE_SERVICE_ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(String message, Long code) {
        super(message, code);
    }
}
