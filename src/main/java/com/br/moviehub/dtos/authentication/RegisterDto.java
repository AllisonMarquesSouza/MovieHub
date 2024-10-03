package com.br.moviehub.dtos.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterDto {
    @NotBlank(message = "Username can't be null")
    @Size(min = 3, message = "Must be at least 3 characters long")
    @Size(max = 30, message = "Must be a maximum of 30 characters")
    private String username;

    @NotBlank(message = "Password can't be  null")
    @Size(min = 8, message = "Must be at least 8 characters long")
    @Size(max = 255, message = "Must be a maximum of 255 characters")
    private String password;

    @NotBlank(message = "Email can't be  null")
    @Email(message = "Email format incorrect, check it")
    private String email;
}
