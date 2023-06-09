package com.flystonedev.cutomer.exeption.config;

import com.flystonedev.cutomer.exeption.ErrorWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorWrapper.class)
    protected ResponseEntity handleGlobalException(ErrorWrapper e, Locale locale){
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }
    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception e, Locale locale){
        return ResponseEntity
                .badRequest()
                .body("Exception occurred inside "+e);
    }
}
