package com.example.demo_spring_boot.controller;

import com.example.demo_spring_boot.entity.Product;
import com.example.demo_spring_boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tests")
public class TestDbController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable String id) {
        return productRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @RequestBody Product updatedProduct) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setName(updatedProduct.getName());
                    p.setQuantity(updatedProduct.getQuantity());
                    p.setPrice(updatedProduct.getPrice());
                    return productRepository.save(p);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        productRepository.deleteById(id);
    }
}
