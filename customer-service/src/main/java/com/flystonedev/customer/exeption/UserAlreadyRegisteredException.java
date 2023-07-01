package com.flystonedev.customer.exeption;

public class UserAlreadyRegisteredException extends ErrorWrapper{
    public UserAlreadyRegisteredException(String message, Long code) {
        super(message, code);
    }
}
