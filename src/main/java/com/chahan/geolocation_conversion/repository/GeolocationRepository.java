package com.chahan.geolocation_conversion.repository;

import com.chahan.geolocation_conversion.model.data.GeolocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocationRepository extends JpaRepository<GeolocationEntity, Long> {

    GeolocationEntity getBySearchHash(String searchHash);

    GeolocationEntity getByLonAndLat(String lon, String lat);
}
