package com.chahan.geolocation_conversion.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestApiException extends BaseApiException {

    public BadRequestApiException(String message) {
        super(BAD_REQUEST, message);
    }
}
