package com.chahan.geolocation_conversion.client.model;

import java.util.Set;
import lombok.Data;

@Data
public class BingMapsApiResponse {

  private Set<ResourceSetDto> resourceSets;
}
