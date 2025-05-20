package com.example.demo_spring_boot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    USER_EXISTED (1001, "Username existed", HttpStatus.BAD_REQUEST ),
    USER_NOT_EXISTED (1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED (1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    KEY_INVALID (1007, "Invalid message key", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED (1008, "You do not have permission", HttpStatus.FORBIDDEN),
    PRODUCT_NOT_FOUND(2001, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_NAME_REQUIRED(2002, "Product name is required", HttpStatus.BAD_REQUEST),
    PRODUCT_DUPLICATED(2003, "Product already exists", HttpStatus.BAD_REQUEST),
    PRODUCT_DESCRIPTION_TOO_LONG(2004, "Description cannot exceed 500 characters", HttpStatus.BAD_REQUEST),
    PRODUCT_QUANTITY_INVALID(2005, "Quantity must be greater than or equal to 1", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_INVALID(2006, "Price must be greater than 0", HttpStatus.BAD_REQUEST),
    SIGNATURE_INVALID(2007, "Invalid signature", HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED (2008, "Token expired", HttpStatus.BAD_REQUEST),

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
