package com.example.DigitalLibraryStore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank(message = "${message.users.name.blank_name}")
        String name,
        @Email(message = "${message.users.email.invalid_format}")
        String email,
        String password,
        boolean enabled
) {
}
