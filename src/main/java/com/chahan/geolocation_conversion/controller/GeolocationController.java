package com.chahan.geolocation_conversion.controller;

import com.chahan.geolocation_conversion.model.dto.GeolocationRequest;
import com.chahan.geolocation_conversion.model.dto.GeolocationResponse;
import com.chahan.geolocation_conversion.service.GeolocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GeolocationController {

    private final GeolocationService geolocationService;

    @GetMapping("/geolocation")
    public GeolocationResponse getGeolocation(GeolocationRequest request) {
        return geolocationService.getGeolocation(request);
    }
}
