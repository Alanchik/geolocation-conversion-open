package com.chahan.geolocation_conversion.exception.handler;

import com.chahan.geolocation_conversion.exception.BaseApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity<ApiError> handle(BaseApiException exception) {
        return new ResponseEntity<>(ApiError.builder()
                .status(exception.getStatus())
                .message(exception.getMessage())
                .build(), exception.getStatus());
    }
}
