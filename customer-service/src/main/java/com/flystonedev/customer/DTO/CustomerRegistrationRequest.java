package com.flystonedev.customer.DTO;


public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password
        ) {}
