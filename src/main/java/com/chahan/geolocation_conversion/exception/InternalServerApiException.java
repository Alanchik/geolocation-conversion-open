package com.chahan.geolocation_conversion.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalServerApiException extends BaseApiException {

    public InternalServerApiException(String message) {
        super(INTERNAL_SERVER_ERROR, message);
    }
}
