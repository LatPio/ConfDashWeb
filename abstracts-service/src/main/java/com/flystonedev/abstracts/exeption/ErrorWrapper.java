package com.flystonedev.abstracts.exeption;

public class ErrorWrapper extends RuntimeException {
    private Long code;

    public ErrorWrapper(String message, Long code) {
        super(message);
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}
