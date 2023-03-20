package com.senla.weatheranalyzer.repository;

import com.senla.weatheranalyzer.dto.AVGResponse;
import com.senla.weatheranalyzer.model.Weather;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

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
            SELECT AVG(temperature) AS average_temp,
            AVG(wind_speed) AS average_wind_speed,
            AVG(pressure) AS average_pressure,
            AVG(humidity) AS average_humidity,
            location
            FROM weather
            WHERE date >= $1 AND date <= $2
            GROUP BY location
            """)
    Mono<AVGResponse> findAverageWeather(LocalDate from, LocalDate to);

}
