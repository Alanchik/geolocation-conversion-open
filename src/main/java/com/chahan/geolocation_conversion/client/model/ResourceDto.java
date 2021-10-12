package com.chahan.geolocation_conversion.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResourceDto {

  @JsonProperty("name")
  private String address;
  private PointDto point;
}
