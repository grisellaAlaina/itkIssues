package org.example.itkissues.configuration;

import lombok.RequiredArgsConstructor;
import org.example.itkissues.model.Role;
import org.example.itkissues.model.User;
import org.example.itkissues.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            userRepository.save(new User(null, "ivan", passwordEncoder.encode("sa"), Role.USER, true, 0));
            userRepository.save(new User(null, "moder", passwordEncoder.encode("sa"), Role.MODERATOR, true, 0));
            userRepository.save(new User(null, "admin", passwordEncoder.encode("sa"), Role.SUPER_ADMIN, true, 0));
        };
    }
}