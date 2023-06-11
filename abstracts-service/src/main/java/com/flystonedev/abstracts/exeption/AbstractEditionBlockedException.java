package com.flystonedev.abstracts.exeption;


import com.flystonedev.abstracts.exeption.config.GlobalErrorCode;

public class AbstractEditionBlockedException extends ErrorWrapper{

    public AbstractEditionBlockedException() {
        super("Abstract edition blocked", GlobalErrorCode.ERROR_ABSTRACT_BLOCKED_FOR_EDITION);
    }
    public AbstractEditionBlockedException(String message, Long code) {
        super(message, code);
    }
}
