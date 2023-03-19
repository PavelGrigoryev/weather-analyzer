package com.senla.weatheranalyzer.util.testbuilder;

import com.senla.weatheranalyzer.model.Weather;
import com.senla.weatheranalyzer.util.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aWeather")
@With
public class WeatherTestBuilder implements TestBuilder<Weather> {

    private Long id = 1L;
    private BigDecimal temperature = BigDecimal.TEN;
    private BigDecimal windSpeed = BigDecimal.ONE;
    private BigDecimal pressure = BigDecimal.valueOf(1232.23);
    private Integer humidity = 36;
    private String condition = "Warm";
    private String location = "Minsk";
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();

    @Override
    public Weather build() {
        return Weather.builder()
                .id(id)
                .temperature(temperature)
                .windSpeed(windSpeed)
                .pressure(pressure)
                .humidity(humidity)
                .condition(condition)
                .location(location)
                .date(date)
                .time(time)
                .build();
    }

}
