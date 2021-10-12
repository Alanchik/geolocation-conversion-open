package com.chahan.geolocation_conversion.model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "geolocations")
public class GeolocationEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String searchHash;
    private String address;
    private String lon;
    private String lat;
}
