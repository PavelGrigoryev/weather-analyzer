package com.senla.weatheranalyzer.dto;

import jakarta.validation.constraints.Pattern;

public record AVGRequest(
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "The date 'from' must be in the format yyyy-MM-dd")
        String from,
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "The date 'to' must be in the format yyyy-MM-dd")
        String to
) {
}
