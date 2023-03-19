package com.senla.weatheranalyzer.service;

import com.senla.weatheranalyzer.dto.AVGTempRequest;
import com.senla.weatheranalyzer.dto.AVGTempResponse;
import com.senla.weatheranalyzer.dto.WeatherDto;
import reactor.core.publisher.Mono;

public interface WeatherService {

    Mono<WeatherDto> findCurrentWeather();

    Mono<AVGTempResponse> findAverageWeather(AVGTempRequest request);

}
