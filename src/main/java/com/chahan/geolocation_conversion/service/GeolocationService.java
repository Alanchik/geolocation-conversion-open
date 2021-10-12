package com.chahan.geolocation_conversion.service;

import com.chahan.geolocation_conversion.exception.BadRequestApiException;
import com.chahan.geolocation_conversion.mapper.GeolocationMapper;
import com.chahan.geolocation_conversion.model.data.GeolocationEntity;
import com.chahan.geolocation_conversion.model.dto.Coordinates;
import com.chahan.geolocation_conversion.model.dto.Geolocation;
import com.chahan.geolocation_conversion.model.dto.GeolocationRequest;
import com.chahan.geolocation_conversion.model.dto.GeolocationResponse;
import com.chahan.geolocation_conversion.repository.GeolocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.chahan.geolocation_conversion.util.Constants.ERROR_BAD_REQUEST_PARAMETERS;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class GeolocationService {

    private final MapService mapService;
    private final GeolocationMapper geolocationMapper;
    private final GeolocationRepository geolocationRepository;
    private final GeolocationFormatter geolocationRequestFormatter;

    public GeolocationResponse getGeolocation(GeolocationRequest request) {
        String search = request.getSearch();
        String lon = request.getLon();
        String lat = request.getLat();
        if (nonNull(search)) {
            return getGeolocationBySearchQuery(search);
        } else if (nonNull(lon) && nonNull(lat)) {
            return getGeolocationByCoordinates(lon, lat);
        }
        throw new BadRequestApiException(ERROR_BAD_REQUEST_PARAMETERS);
    }

    private GeolocationResponse getGeolocationBySearchQuery(String search) {
        String searchHash = geolocationRequestFormatter.formatAndHashSearchQuery(search);
        GeolocationEntity existingGeolocation = geolocationRepository.getBySearchHash(searchHash);
        if (nonNull(existingGeolocation)) {
            return geolocationMapper.map(existingGeolocation, search);
        }
        Geolocation geolocation = mapService.getGeolocation(search);
        GeolocationEntity geolocationEntity = geolocationMapper.map(geolocation, searchHash, search);
        GeolocationEntity savedGeolocation = geolocationRepository.save(geolocationEntity);
        return geolocationMapper.map(savedGeolocation, search);
    }

    private GeolocationResponse getGeolocationByCoordinates(String lon, String lat) {
        Coordinates coordinates = geolocationRequestFormatter.formatCoordinates(lon, lat);
        GeolocationEntity existingGeolocation = geolocationRepository.findFirstByLonAndLat(coordinates.getLon(), coordinates.getLat());
        String search = String.join(", ", coordinates.getLon(), coordinates.getLat());
        if (nonNull(existingGeolocation)) {
            return geolocationMapper.map(existingGeolocation, search);
        }
        Geolocation geolocation = mapService.getGeolocation(search);
        GeolocationEntity geolocationEntity = geolocationMapper.mapWithCoordinates(geolocation, coordinates.getLon(), coordinates.getLat());
        GeolocationEntity saveGeolocation = geolocationRepository.save(geolocationEntity);
        return geolocationMapper.map(saveGeolocation, search);
    }
}
