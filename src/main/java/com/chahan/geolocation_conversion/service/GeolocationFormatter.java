package com.chahan.geolocation_conversion.service;

import com.chahan.geolocation_conversion.model.dto.Coordinates;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.chahan.geolocation_conversion.util.Constants.COORDINATES_DECIMALS_SIZE;

@Component
public class GeolocationFormatter {

    public String formatAndHashSearchQuery(final String searchQuery) {
        String result = searchQuery;
        result = result.replaceAll("[,.]", " ");
        result = result.replaceAll("\\s{2,}", " ").trim();
        result = result.toLowerCase();
        String[] queryKeyWords = result.split(" ");
        Arrays.sort(queryKeyWords);
        return DigestUtils.md5DigestAsHex(String.join(" ", queryKeyWords).getBytes(StandardCharsets.UTF_8));
    }

    public Coordinates formatCoordinates(String lon, String lat) {
        lon = formatDecimals(lon);
        lat = formatDecimals(lat);
        return Coordinates.builder()
                .lon(lon)
                .lat(lat)
                .build();
    }

    public String formatDecimals(String val) {
        String decimals = val.split("\\.")[1];
        int accuracy = decimals.length();
        if (accuracy >= COORDINATES_DECIMALS_SIZE) {
            decimals = decimals.substring(0, COORDINATES_DECIMALS_SIZE);
        } else {
            StringBuilder decimalsBuilder = new StringBuilder(decimals);
            for (int i = accuracy; i < COORDINATES_DECIMALS_SIZE; i++) {
                decimalsBuilder.append("0");
            }
            decimals = decimalsBuilder.toString();
        }
        return String.join(".", val.split("\\.")[0], decimals);
    }
}
