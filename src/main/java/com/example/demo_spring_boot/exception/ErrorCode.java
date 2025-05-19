package com.example.demo_spring_boot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    PRODUCT_NOT_FOUND(2001, "Product not found", HttpStatus.NOT_FOUND),
    KEY_INVALID (1007, "Invalid message key", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_REQUIRED(2002, "Product name is required", HttpStatus.BAD_REQUEST),
    PRODUCT_DUPLICATED(2003, "Product already exists", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(2999, "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
