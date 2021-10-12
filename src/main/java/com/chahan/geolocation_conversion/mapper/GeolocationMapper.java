package com.chahan.geolocation_conversion.mapper;

import com.chahan.geolocation_conversion.client.model.BingMapsApiResponse;
import com.chahan.geolocation_conversion.client.model.ResourceDto;
import com.chahan.geolocation_conversion.exception.BadRequestApiException;
import com.chahan.geolocation_conversion.model.data.GeolocationEntity;
import com.chahan.geolocation_conversion.model.dto.Geolocation;
import com.chahan.geolocation_conversion.model.dto.GeolocationResponse;
import com.chahan.geolocation_conversion.service.GeolocationFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static com.chahan.geolocation_conversion.util.Constants.ERROR_GEOLOCATION_NOT_FOUND;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedSourcePolicy = IGNORE)
public abstract class GeolocationMapper {

    @Autowired
    private GeolocationFormatter geolocationFormatter;

    public abstract GeolocationResponse map(GeolocationEntity source, String search);

    @Mapping(target = "address", expression = "java( getAddress(source) )")
    @Mapping(target = "lon", expression = "java( getLon(source) )")
    @Mapping(target = "lat", expression = "java( getLat(source) )")
    public abstract Geolocation map(BingMapsApiResponse source, String search);

    public abstract GeolocationEntity map(Geolocation source, String searchHash, String search);

    @Mapping(target = "lon", source = "lon")
    @Mapping(target = "lat", source = "lat")
    public abstract GeolocationEntity mapWithCoordinates(Geolocation source, String lon, String lat);

    protected String getAddress(BingMapsApiResponse source) {
        return getFirstResource(source).getAddress();
    }

    protected String getLon(BingMapsApiResponse source) {
        return geolocationFormatter.formatDecimals(getFirstResource(source).getPoint().getCoordinates()[0]);
    }

    protected String getLat(BingMapsApiResponse source) {
        return geolocationFormatter.formatDecimals(getFirstResource(source).getPoint().getCoordinates()[1]);
    }

    private ResourceDto getFirstResource(BingMapsApiResponse source) {
        return source.getResourceSets().stream()
                .findFirst().orElseThrow(() -> new BadRequestApiException(ERROR_GEOLOCATION_NOT_FOUND))
                .getResources().stream().findFirst().orElseThrow(() -> new BadRequestApiException(ERROR_GEOLOCATION_NOT_FOUND));
    }
}
