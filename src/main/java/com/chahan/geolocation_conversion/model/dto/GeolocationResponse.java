package com.chahan.geolocation_conversion.model.dto;

import lombok.Data;

@Data
public class GeolocationResponse {

    private String search;
    private String address;
    private String lon;
    private String lat;
}
