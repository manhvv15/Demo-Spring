package com.example.demo_spring_boot.service;

import com.example.demo_spring_boot.dto.request.ProductRequest;
import com.example.demo_spring_boot.dto.response.ProductResponse;
import com.example.demo_spring_boot.entity.Product;
import com.example.demo_spring_boot.exception.AppException;
import com.example.demo_spring_boot.exception.ErrorCode;
import com.example.demo_spring_boot.mapper.ProductMapper;
import com.example.demo_spring_boot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public Product getById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public ProductResponse create(ProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_DUPLICATED);
        }

        Product saved = productRepository.save(productMapper.toProduct(request));
        return productMapper.toProductResponse(saved);
    }

    public Product update(String id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (productRepository.existsByNameAndIdNot(updatedProduct.getName(), id)) {
            throw new AppException(ErrorCode.PRODUCT_DUPLICATED);
        }

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        product.setQuantity(updatedProduct.getQuantity());

        return productRepository.save(product);
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
