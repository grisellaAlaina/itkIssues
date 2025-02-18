package org.example.itkissues.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.itkissues.jwt.JWTUtils;
import org.example.itkissues.service.OurUserDetailedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final OurUserDetailedService userService;
    private final JWTUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userService.resetFailedAttempts(authRequest.getUsername());

            final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
            final String jwt = jwtUtils.generateToken(userDetails);

            logger.info("Успешная аутентификация пользователя: {}", authRequest.getUsername());
            logger.debug("Сгенерирован JWT для пользователя {}: {}", authRequest.getUsername(), jwt);

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (BadCredentialsException e){
            boolean isLocked = userService.increaseFailedAttempts(authRequest.getUsername());

            if(isLocked){
                logger.warn("Аккаунт пользователя {} был заблокирован из-за превышения количества неудачных попыток входа.", authRequest.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Аккаунт заблокирован из-за превышения количества неудачных попыток входа.");
            }

            logger.warn("Неудачная попытка входа для пользователя: {}", authRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверное имя пользователя или пароль");
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AuthRequest {
        private String username;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AuthResponse {
        private String jwt;
    }
}