package com.br.moviehub.service;

import com.br.moviehub.dtos.authentication.ChangePasswordDto;
import com.br.moviehub.dtos.authentication.LoginDto;
import com.br.moviehub.dtos.authentication.RegisterDto;
import com.br.moviehub.dtos.authentication.TokenResponseDto;
import com.br.moviehub.enums.UserRole;
import com.br.moviehub.exception.personalizedExceptions.BadRequestException;
import com.br.moviehub.model.User;
import com.br.moviehub.repository.UserRepository;
import com.br.moviehub.security.TokenService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService implements UserDetailsService {
    private final ApplicationContext context;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public TokenResponseDto login(LoginDto data) {
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return new TokenResponseDto(token);
        } catch (Exception e) {
            throw new BadRequestException("Don't possible to make login , check your username and password");
        }
    }

    @Transactional
    public void changePassword(ChangePasswordDto data){
        User userFound = userRepository.findByUsernameToUserType(data.getUsername())
                .orElseThrow(()-> new EntityNotFoundException("Username not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean checkingPassword = encoder.matches(data.getOldPassword(), userFound.getPassword());
        if (!checkingPassword){
            throw new BadRequestException("Old password is incorrect , check it");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getNewPassword());
        userFound.setPassword(encryptedPassword);
        userRepository.save(userFound);
    }

    @Transactional
    public User register(RegisterDto registerDto) {
        if (this.userRepository.findByUsername(registerDto.getUsername()) != null) {
            String message = "Username already exists!";
            throw new EntityExistsException(message);
        }
        if (this.userRepository.findByEmail(registerDto.getEmail()) != null) {
            String message = "Email already exists!";
            throw new EntityExistsException(message);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.getPassword());
        User newUser = new User(registerDto.getUsername(), encryptedPassword, registerDto.getEmail(), UserRole.USER);
        return this.userRepository.save(newUser);
    }

}