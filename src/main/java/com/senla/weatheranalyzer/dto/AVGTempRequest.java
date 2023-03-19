package com.senla.weatheranalyzer.dto;

import java.time.LocalDate;

public record AVGTempRequest(
        LocalDate from,
        LocalDate to
) {
}
