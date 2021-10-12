package com.chahan.geolocation_conversion.model.dto;

import lombok.Data;

@Data
public class GeolocationRequest {

    private String search;
    private String lon;
    private String lat;
}
