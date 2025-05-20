package com.example.demo_spring_boot.service;

import com.example.demo_spring_boot.dto.request.RegisterRequest;
import com.example.demo_spring_boot.dto.response.ApiResponse;
import com.example.demo_spring_boot.entity.User;
import com.example.demo_spring_boot.enums.Role;
import com.example.demo_spring_boot.exception.AppException;
import com.example.demo_spring_boot.exception.ErrorCode;
import com.example.demo_spring_boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<String>  createUser(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create and save user
        User user = User.builder()
                .username(request.getUsername())
                .password(hashedPassword)
                .role(Role.USER) // hoặc lấy từ request nếu bạn cho chọn
                .build();

        userRepository.save(user);
        return ApiResponse.<String>builder()
                .code(200)
                .message("User registered successfully")
                .result("OK")
                .build();
    }
}
