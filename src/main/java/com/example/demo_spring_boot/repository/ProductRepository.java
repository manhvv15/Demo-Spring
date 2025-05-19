package com.example.demo_spring_boot.repository;

import com.example.demo_spring_boot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
}
