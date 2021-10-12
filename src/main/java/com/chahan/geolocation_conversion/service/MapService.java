package com.chahan.geolocation_conversion.service;

import com.chahan.geolocation_conversion.client.BingMapsClient;
import com.chahan.geolocation_conversion.exception.BadRequestApiException;
import com.chahan.geolocation_conversion.exception.InternalServerApiException;
import com.chahan.geolocation_conversion.mapper.GeolocationMapper;
import com.chahan.geolocation_conversion.model.dto.Geolocation;
import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.chahan.geolocation_conversion.util.Constants.ERROR_GEOLOCATION_NOT_FOUND;
import static com.chahan.geolocation_conversion.util.Constants.ERROR_MAPS_API;

@Service
@RequiredArgsConstructor
public class MapService {

    private final BingMapsClient mapsClient;
    private final GeolocationMapper geolocationMapper;

    @Value("${geolocation-conversion.maps-api.key}")
    private String mapsApiKey;

    public Geolocation getGeolocation(String search) {
        try {
            return geolocationMapper.map(mapsClient.getGeolocation(search, mapsApiKey), search);
        } catch (FeignClientException ex) {
            if (ex.status() == 400 || ex.status() == 404) {
                throw new BadRequestApiException(ERROR_GEOLOCATION_NOT_FOUND);
            }
            throw new InternalServerApiException(ERROR_MAPS_API);
        }
    }
}
