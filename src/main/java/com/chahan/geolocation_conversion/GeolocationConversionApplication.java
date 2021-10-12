package com.chahan.geolocation_conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GeolocationConversionApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeolocationConversionApplication.class, args);
    }
}
