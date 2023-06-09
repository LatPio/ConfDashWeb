package com.flystonedev.cutomer.exeption;

public class UserAlreadyRegisteredException extends ErrorWrapper{
    public UserAlreadyRegisteredException(String message, Long code) {
        super(message, code);
    }
}
