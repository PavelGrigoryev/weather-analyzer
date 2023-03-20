package com.senla.weatheranalyzer.dto;

import java.time.LocalDate;

public record AVGRequest(
        LocalDate from,
        LocalDate to
) {
}
