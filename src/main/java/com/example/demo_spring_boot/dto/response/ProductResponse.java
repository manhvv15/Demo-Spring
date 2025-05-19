package com.example.demo_spring_boot.dto.response;

import jakarta.persistence.Column;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String id;

    @Column(nullable = false)
    private String name;

    private String description;

    private int quantity;

    private float price;
}
