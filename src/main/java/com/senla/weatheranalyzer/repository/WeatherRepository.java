package com.senla.weatheranalyzer.repository;

import com.senla.weatheranalyzer.model.Weather;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface WeatherRepository extends ReactiveCrudRepository<Weather, Long> {

    @Query("""
            SELECT *
            FROM weather
            ORDER BY id DESC
            LIMIT 1
            """)
    Mono<Weather> findCurrentWeather();

    @Query("""
            SELECT AVG(temperature)
            FROM weather
            WHERE date >= $1 AND date <= $2
            """)
    Mono<BigDecimal> findAverageTemperature(LocalDate from, LocalDate to);

}
