package com.br.moviehub.dtos.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordDto{
    @NotBlank(message = "The username can't be null")
    private String username;

    @NotBlank(message = "The oldPassword can't be null")
    private String oldPassword;

    @NotBlank(message = "The newPassword can't be null")
    @Size(min = 8, message = "Must be at least 8 characters long")
    @Size(max = 255, message = "Must be a maximum of 255 characters")
    private String newPassword;
}