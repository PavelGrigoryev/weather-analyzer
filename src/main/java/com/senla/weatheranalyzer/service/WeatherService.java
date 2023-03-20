package com.senla.weatheranalyzer.service;

import com.senla.weatheranalyzer.dto.AVGRequest;
import com.senla.weatheranalyzer.dto.AVGResponse;
import com.senla.weatheranalyzer.dto.WeatherDto;
import reactor.core.publisher.Mono;

public interface WeatherService {

    Mono<WeatherDto> findCurrentWeather();

    Mono<AVGResponse> findAverageWeather(AVGRequest request);

}
