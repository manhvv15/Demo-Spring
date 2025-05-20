package com.example.demo_spring_boot.configuration;


import com.example.demo_spring_boot.entity.User;
import com.example.demo_spring_boot.enums.Role;
import com.example.demo_spring_boot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){ //khởi chạy mỗi khi application start
        return args ->{
            if(userRepository.findByUsername("admin").isEmpty()){

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .email("admin@gmail.com")
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(user);
            }
        };


    }
}
