package com.senla.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AVGTempResponse(
        @JsonProperty("average_temp")
        Double averageTemp,
        @JsonProperty("average_wind_speed")
        Double averageWindSpeed,
        @JsonProperty("average_pressure")
        Double averagePressure,
        @JsonProperty("average_humidity")
        Double averageHumidity,
        String location
) {
}
