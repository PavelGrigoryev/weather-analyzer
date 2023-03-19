package com.senla.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "temperature",
        "wind_speed",
        "pressure",
        "humidity",
        "condition",
        "location"
})
public record WeatherDto(
        Double temperature,
        @JsonProperty("wind_speed")
        Double windSpeed,
        Double pressure,
        Integer humidity,
        String condition,
        String location
) {
}
