package com.senla.weatheranalyzer.service.impl;

import com.senla.weatheranalyzer.dto.AVGRequest;
import com.senla.weatheranalyzer.dto.AVGResponse;
import com.senla.weatheranalyzer.dto.WeatherDto;
import com.senla.weatheranalyzer.mapper.WeatherMapper;
import com.senla.weatheranalyzer.repository.WeatherRepository;
import com.senla.weatheranalyzer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
     * Method for finding the average weather for a specified period of time.
     *
     * @param request an AVGRequest object containing the start and end dates of the period.
     * @return Mono with an AVGResponse object containing the average weather for the specified period.
     */
    @Override
    public Mono<AVGResponse> findAverageWeather(AVGRequest request) {
        return weatherRepository.findAverageWeather(request.from(), request.to())
                .log("WeatherService findAverageWeather");
    }

}
