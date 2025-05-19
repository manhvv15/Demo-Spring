package com.example.demo_spring_boot.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @Column(nullable = false)
    private String name;

    private String description;

    private int quantity;

    private float price;
}
