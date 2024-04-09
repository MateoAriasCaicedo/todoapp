package com.codecrafters.todoapp.domain.users;

public record UserCreationDTO(
    String firstName, String lastName, String username, String email, String password) {}
