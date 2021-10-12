package com.chahan.geolocation_conversion.util;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Constants {

    public static final int COORDINATES_DECIMALS_SIZE = 6;

    public static final String ERROR_GEOLOCATION_NOT_FOUND = "Nothing found by request";
    public static final String ERROR_BAD_REQUEST_PARAMETERS = "Define 'search' parameter or both of 'lon' and 'lat'";
    public static final String ERROR_MAPS_API = "Maps api is unavailable";
}
