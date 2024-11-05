package com.bookverse.development.packapps.automation.models;

public record BookverseUser(
    String name,
    String lastName,
    String phone,
    String occupation,
    String address,
    String username,
    String password,
    String email,
    String gender,
    String book
) {}