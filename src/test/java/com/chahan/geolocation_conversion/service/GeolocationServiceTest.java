package com.chahan.geolocation_conversion.service;

import com.chahan.geolocation_conversion.mapper.GeolocationMapper;
import com.chahan.geolocation_conversion.model.data.GeolocationEntity;
import com.chahan.geolocation_conversion.model.dto.Coordinates;
import com.chahan.geolocation_conversion.model.dto.GeolocationRequest;
import com.chahan.geolocation_conversion.repository.GeolocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class GeolocationServiceTest {

    @InjectMocks
    private GeolocationService geolocationService;
    @Mock
    private GeolocationFormatter geolocationFormatter;
    @Mock
    private GeolocationRepository geolocationRepository;
    @Mock
    private GeolocationMapper geolocationMapper;
    @Mock
    private MapService mapService;

    @Test
    public void should_not_call_map_service_if_geolocation_exists_in_db_by_search_hash() {
        final String search = "search request";
        final String searchHash = "123";

        GeolocationRequest request = new GeolocationRequest();
        request.setSearch(search);

        Mockito.when(geolocationFormatter.formatAndHashSearchQuery(eq(request.getSearch()))).thenReturn(searchHash);
        Mockito.when(geolocationRepository.getBySearchHash(eq(searchHash))).thenReturn(new GeolocationEntity());

        geolocationService.getGeolocation(request);

        Mockito.verify(geolocationRepository, Mockito.times(1)).getBySearchHash(eq(searchHash));
        Mockito.verify(geolocationMapper, Mockito.times(1)).map(any(GeolocationEntity.class), eq(search));
        Mockito.verify(mapService, Mockito.times(0)).getGeolocation(anyString());
    }

    @Test
    public void should_not_call_map_service_if_geolocation_exists_in_db_by_coordinates() {
        final String lon = "51";
        final String lat = "15";

        final String formattedLon = "51.000";
        final String formattedLat = "15.000";

        Coordinates coordinates = Coordinates.builder()
                .lon(formattedLon)
                .lat(formattedLat)
                .build();

        GeolocationRequest request = new GeolocationRequest();
        request.setLon(lon);
        request.setLat(lat);

        Mockito.when(geolocationFormatter.formatCoordinates(eq(request.getLon()), eq(request.getLat()))).thenReturn(coordinates);
        Mockito.when(geolocationRepository.findFirstByLonAndLat(eq(coordinates.getLon()), eq(coordinates.getLat()))).thenReturn(new GeolocationEntity());

        geolocationService.getGeolocation(request);

        Mockito.verify(geolocationRepository, Mockito.times(1)).findFirstByLonAndLat(eq(coordinates.getLon()), eq(coordinates.getLat()));
        Mockito.verify(geolocationMapper, Mockito.times(1)).map(any(GeolocationEntity.class), anyString());
        Mockito.verify(mapService, Mockito.times(0)).getGeolocation(anyString());
    }
}
