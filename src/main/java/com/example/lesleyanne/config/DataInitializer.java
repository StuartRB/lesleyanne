package com.example.lesleyanne.config;

import com.example.lesleyanne.model.UserAccount;
import com.example.lesleyanne.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserAccountRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                repo.save(new UserAccount("admin", encoder.encode("admin123"), "ROLE_ADMIN"));
                repo.save(new UserAccount("user", encoder.encode("user123"), "ROLE_USER"));
            }
        };
    }
}

