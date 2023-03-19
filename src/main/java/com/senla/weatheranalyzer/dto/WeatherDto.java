package com.senla.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({
        "temperature",
        "wind_speed",
        "pressure",
        "humidity",
        "condition",
        "location"
})
public record WeatherDto(
        BigDecimal temperature,
        @JsonProperty("wind_speed")
        BigDecimal windSpeed,
        BigDecimal pressure,
        Integer humidity,
        String condition,
        String location
) {
}
