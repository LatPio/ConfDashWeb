package com.flystonedev.event.exeption;


import com.flystonedev.event.exeption.config.GlobalErrorCode;

public class ClientCallException extends ErrorWrapper {

    public ClientCallException() {
        super("Exception occurred when call to different service", GlobalErrorCode.ERROR_EVENT_SERVICE_ENDPOINT_ERROR);
    }
    public ClientCallException(String message, Long code) {
        super(message, code);
    }
}
