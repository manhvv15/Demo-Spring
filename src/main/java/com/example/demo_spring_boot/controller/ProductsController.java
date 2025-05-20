package com.example.demo_spring_boot.controller;

import com.example.demo_spring_boot.dto.request.ProductRequest;
import com.example.demo_spring_boot.dto.response.ProductResponse;
import com.example.demo_spring_boot.entity.Product;
import com.example.demo_spring_boot.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;
    @GetMapping
    public List<ProductResponse> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable String id){
        return productService.getById(id);
    }

    @PostMapping
    public ProductResponse create(@RequestBody @Valid ProductRequest product){
        return productService.create(product);
    }
    @PutMapping("/{id}")
    public Product update(@PathVariable String id,@RequestBody Product product){
        return productService.update(id,product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        productService.delete(id);
    }
}
