package org.example.itkissues.controller;

import lombok.AllArgsConstructor;
import org.example.itkissues.model.User;
import org.example.itkissues.repository.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping()
@AllArgsConstructor
public class Controller {
    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/home")
    public String publicEndpoint() {
        return "welcomee";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('SUPER_ADMIN')")
    public String userEndpoint() {
        return "USER endpoint";
    }

    @GetMapping("/moderator")
    @PreAuthorize("hasAnyRole('MODERATOR','SUPER_ADMIN')")
    public String moderatorEndpoint() {
        return "MODERATOR endpoint";
    }

    @GetMapping("/admin")
    @Secured("SUPER_ADMIN")
    public String adminEndpoint() {
        return "ADMIN endpoint";
    }
}
