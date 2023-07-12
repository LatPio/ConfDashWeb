package com.flystonedev.basket.exeption;


import com.flystonedev.basket.exeption.config.GlobalErrorCode;

public class EntityNotFoundException extends ErrorWrapper{

    public EntityNotFoundException() {
        super("Exception Entity not found", GlobalErrorCode.ERROR_BASKET_SERVICE_ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(String message, Long code) {
        super(message, code);
    }
}
