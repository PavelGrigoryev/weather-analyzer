package com.senla.weatheranalyzer.service.impl;

import com.senla.weatheranalyzer.dto.AVGTempRequest;
import com.senla.weatheranalyzer.dto.AVGTempResponse;
import com.senla.weatheranalyzer.dto.WeatherDto;
import com.senla.weatheranalyzer.mapper.WeatherMapper;
import com.senla.weatheranalyzer.repository.WeatherRepository;
import com.senla.weatheranalyzer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper = Mappers.getMapper(WeatherMapper.class);

    /**
     * Method for finding the current weather.
     *
     * @return Mono with a WeatherDto object representing the current weather.
     */
    @Override
    public Mono<WeatherDto> findCurrentWeather() {
        return weatherRepository.findCurrentWeather()
                .map(weatherMapper::toDto)
                .log("WeatherService findCurrentWeather");
    }

    /**
     * Method for finding the average temperature for a specified period of time.
     *
     * @param request an AVGTempRequest object containing the start and end dates of the period.
     * @return Mono with an AVGTempResponse object containing the average temperature for the specified period.
     */
    @Override
    public Mono<AVGTempResponse> findAverageTemperature(AVGTempRequest request) {
        return weatherRepository.findAverageTemperature(request.from(), request.to())
                .map(bigDecimal -> new BigDecimal(bigDecimal.stripTrailingZeros().toPlainString()))
                .map(AVGTempResponse::new)
                .log("WeatherService findAverageTemperature");
    }

}
