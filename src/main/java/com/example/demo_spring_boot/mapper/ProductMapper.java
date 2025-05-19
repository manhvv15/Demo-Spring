package com.example.demo_spring_boot.mapper;

import com.example.demo_spring_boot.dto.request.ProductRequest;
import com.example.demo_spring_boot.dto.response.ProductResponse;
import com.example.demo_spring_boot.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest request);
    ProductResponse toProductResponse(Product product);
}
