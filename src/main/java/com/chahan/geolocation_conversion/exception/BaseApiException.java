package com.chahan.geolocation_conversion.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseApiException extends RuntimeException {

    private final HttpStatus status;

    protected BaseApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
