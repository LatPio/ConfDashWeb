package com.flystonedev.localization.exeption.config;

import com.flystonedev.localization.exeption.ErrorWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorWrapper.class)
    protected ResponseEntity handleGlobalException(ErrorWrapper e){
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }
    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception e){
        return ResponseEntity
                .badRequest()
                .body("Exception occurred inside "+e);
    }
}
