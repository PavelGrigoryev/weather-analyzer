package com.senla.weatheranalyzer.service.impl;

import com.senla.weatheranalyzer.dto.AVGRequest;
import com.senla.weatheranalyzer.dto.AVGResponse;
import com.senla.weatheranalyzer.dto.WeatherDto;
import com.senla.weatheranalyzer.mapper.WeatherMapper;
import com.senla.weatheranalyzer.model.Weather;
import com.senla.weatheranalyzer.repository.WeatherRepository;
import com.senla.weatheranalyzer.service.WeatherService;
import com.senla.weatheranalyzer.util.testbuilder.AVGRequestTestBuilder;
import com.senla.weatheranalyzer.util.testbuilder.AVGResponseTestBuilder;
import com.senla.weatheranalyzer.util.testbuilder.WeatherTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @Spy
    private WeatherService weatherService;
    @Mock
    private WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper = Mappers.getMapper(WeatherMapper.class);

    @BeforeEach
    void setUp() {
        weatherService = new WeatherServiceImpl(weatherRepository);
    }

    @Test
    @DisplayName("check findCurrentWeather method should return expected value")
    void checkFindCurrentWeatherShouldReturnExpectedValue() {
        Weather weather = WeatherTestBuilder.aWeather().build();
        WeatherDto weatherDto = weatherMapper.toDto(weather);

        doReturn(Mono.just(weather))
                .when(weatherRepository)
                .findCurrentWeather();

        Mono<WeatherDto> result = weatherService.findCurrentWeather();

        StepVerifier.create(result)
                .expectNext(weatherDto)
                .verifyComplete();
    }

    @Test
    @DisplayName("check findAverageWeather method should return expected value")
    void checkFindAverageWeatherShouldReturnExpectedValue() {
        AVGRequest request = AVGRequestTestBuilder.aAVGTempRequest().build();
        AVGResponse response = AVGResponseTestBuilder.aAVGTempResponse().build();

        doReturn(Mono.just(response))
                .when(weatherRepository)
                .findAverageWeather(request.from(), request.to());

        Mono<AVGResponse> result = weatherService.findAverageWeather(request);

        StepVerifier.create(result)
                .expectNext(response)
                .verifyComplete();
    }

}
