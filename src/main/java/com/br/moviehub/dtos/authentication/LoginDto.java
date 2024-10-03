package com.br.moviehub.dtos.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginDto {
    @NotBlank(message = "Username can't be null")
    private String username;

    @NotBlank(message = "Password can't be  null")
    private String password;

}

