package com.flystonedev.cutomer.records;


public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
        ) {}
