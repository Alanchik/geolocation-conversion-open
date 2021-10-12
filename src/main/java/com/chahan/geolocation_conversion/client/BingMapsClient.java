package com.chahan.geolocation_conversion.client;

import com.chahan.geolocation_conversion.client.model.BingMapsApiResponse;
import com.chahan.geolocation_conversion.config.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bingMaps", url = "http://dev.virtualearth.net/REST/v1/Locations", configuration = FeignClientsConfiguration.class)
public interface BingMapsClient {

    @GetMapping("/{search}")
    BingMapsApiResponse getGeolocation(@PathVariable("search") String search, @RequestParam("key") String key);
}
