package com.chahan.geolocation_conversion.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinates {

    private String lon;
    private String lat;
}
