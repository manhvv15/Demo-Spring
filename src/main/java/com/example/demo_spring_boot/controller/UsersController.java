package com.example.demo_spring_boot.controller;

import com.example.demo_spring_boot.dto.request.RegisterRequest;
import com.example.demo_spring_boot.dto.response.ApiResponse;
import com.example.demo_spring_boot.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    @PermitAll
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody @Valid RegisterRequest request) {
        return userService.createUser(request);
    }
}
