package com.senla.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record AVGTempResponse(
        @JsonProperty("average_temp")
        BigDecimal averageTemp
) {
}
