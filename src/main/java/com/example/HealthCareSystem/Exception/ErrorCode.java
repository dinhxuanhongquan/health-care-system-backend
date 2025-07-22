package com.example.HealthCareSystem.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1001, "User is not authenticated", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1002, "User is not authorized to perform this action", HttpStatus.FORBIDDEN),

    INVALID_KEY(1003, "Invalid key provided", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1004, "Invalid date of birth provided", HttpStatus.BAD_REQUEST),

    USER_EXISTED(1005, "User already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1006, "User does not exist", HttpStatus.NOT_FOUND),

    USERNAME_VALIDATION(1007, "Username must be at least {min} characters long", HttpStatus.BAD_REQUEST),
    PASSWORD_VALIDATION(1008, "Password must be at least {min} characters long", HttpStatus.BAD_REQUEST),

    UNCATEGORIZED_EXCEPTION(9991, "Uncategorized Error", HttpStatus.INTERNAL_SERVER_ERROR),

    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}


