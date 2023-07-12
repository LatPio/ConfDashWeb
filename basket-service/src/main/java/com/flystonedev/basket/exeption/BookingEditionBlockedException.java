package com.flystonedev.basket.exeption;


import com.flystonedev.basket.exeption.ErrorWrapper;
import com.flystonedev.basket.exeption.config.GlobalErrorCode;

public class BookingEditionBlockedException extends ErrorWrapper {

    public BookingEditionBlockedException() {
        super("This booked event can by deleted by Admin", GlobalErrorCode.ERROR_BASKET_BLOCKED_FOR_EDITION);
    }
    public BookingEditionBlockedException(String message, Long code) {
        super(message, code);
    }
}
