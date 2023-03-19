package com.senla.weatheranalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("weather")
public class Weather {

    @Id
    private Long id;

    private BigDecimal temperature;

    @Column("wind_speed")
    private BigDecimal windSpeed;

    private BigDecimal pressure;

    private Integer humidity;

    private String condition;

    private String location;

    private LocalDate date;

    private LocalTime time;

}
