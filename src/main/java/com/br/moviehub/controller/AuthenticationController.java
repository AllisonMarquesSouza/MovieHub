package com.br.moviehub.controller;

import com.br.moviehub.dtos.authentication.ChangePasswordDto;
import com.br.moviehub.dtos.authentication.LoginDto;
import com.br.moviehub.dtos.authentication.RegisterDto;
import com.br.moviehub.dtos.authentication.TokenResponseDto;
import com.br.moviehub.model.User;
import com.br.moviehub.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary =  "login", method = "POST", description ="Login", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful login",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponseDto.class)
                    ))})
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginDto authenticationDto){
        return ResponseEntity.ok(authenticationService.login(authenticationDto));
    }

    @Operation(summary =  "register", method = "POST", description ="Register new User", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created, successful register",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)
                    ))})
    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody @Valid RegisterDto registerDto){
        return new ResponseEntity<>(authenticationService.register(registerDto), HttpStatus.CREATED);
    }

    @Operation(summary =  "changePassword", method = "PATCH", description ="Change the password", responses = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content, successful change",
                    content = @Content(mediaType = "application/json"
                    ))})
    @PatchMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid ChangePasswordDto data){
        authenticationService.changePassword(data);
        return ResponseEntity.noContent().build();
    }
}
