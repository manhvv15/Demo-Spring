package com.example.demo_spring_boot.exception;

import com.example.demo_spring_boot.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";

    // ✅ Xử lý AppException với ErrorCode
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    // ✅ Xử lý lỗi validate DTO (e.g. @Valid)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.KEY_INVALID;
        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumKey);

            var constraintViolation = exception.getBindingResult()
                    .getAllErrors()
                    .get(0)
                    .unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

        } catch (IllegalArgumentException e) {
            log.warn("Cannot parse error code: {}", enumKey);
        }

        String message = Objects.nonNull(attributes)
                ? mapAttribute(errorCode.getMessage(), attributes)
                : errorCode.getMessage();

        return ResponseEntity.badRequest()
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(message)
                        .build());
    }

    // ✅ Xử lý các lỗi không xác định (500)
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<?>> handleUncategorizedException(Exception exception) {
        log.error("Unexpected error", exception);

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    // ✅ Hàm hỗ trợ thay thế {min} bằng giá trị cụ thể
    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
