package com.br.moviehub.controller;

import com.br.moviehub.dtos.authentication.ChangePasswordDto;
import com.br.moviehub.dtos.authentication.LoginDto;
import com.br.moviehub.dtos.authentication.RegisterDto;
import com.br.moviehub.dtos.authentication.TokenResponseDto;
import com.br.moviehub.model.User;
import com.br.moviehub.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginDto authenticationDto){
        return ResponseEntity.ok(authenticationService.login(authenticationDto));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody @Valid RegisterDto registerDto){
        return new ResponseEntity<>(authenticationService.register(registerDto), HttpStatus.CREATED);
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid ChangePasswordDto data){
        authenticationService.changePassword(data);
        return ResponseEntity.noContent().build();
    }
}
