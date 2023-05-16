package com.flystonedev.cutomer.DTO;


public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
        ) {}
