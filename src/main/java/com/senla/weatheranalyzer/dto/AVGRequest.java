package com.senla.weatheranalyzer.dto;

import jakarta.validation.constraints.Pattern;

public record AVGRequest(
        @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                message = "The date 'from' must be in the format yyyy-MM-dd with valid values for year, month, and day")
        String from,
        @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                message = "The date 'to' must be in the format yyyy-MM-dd with valid values for year, month, and day")
        String to
) {
}
