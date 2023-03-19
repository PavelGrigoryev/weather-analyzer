package com.senla.weatheranalyzer.service.impl;

import com.senla.weatheranalyzer.dto.AVGTempRequest;
import com.senla.weatheranalyzer.dto.AVGTempResponse;
import com.senla.weatheranalyzer.dto.WeatherDto;
import com.senla.weatheranalyzer.mapper.WeatherMapper;
import com.senla.weatheranalyzer.model.Weather;
import com.senla.weatheranalyzer.repository.WeatherRepository;
import com.senla.weatheranalyzer.service.WeatherService;
import com.senla.weatheranalyzer.util.testbuilder.AVGTempRequestTestBuilder;
import com.senla.weatheranalyzer.util.testbuilder.AVGTempResponseTestBuilder;
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
    @DisplayName("check findAverageTemperature method should return expected value")
    void checkFindAverageTemperatureShouldReturnExpectedValue() {
        AVGTempRequest request = AVGTempRequestTestBuilder.aAVGTempRequest().build();
        AVGTempResponse response = AVGTempResponseTestBuilder.aAVGTempResponse().build();

        doReturn(Mono.just(response.averageTemp()))
                .when(weatherRepository)
                .findAverageTemperature(request.from(), request.to());

        Mono<AVGTempResponse> result = weatherService.findAverageTemperature(request);

        StepVerifier.create(result)
                .expectNext(response)
                .verifyComplete();
    }

}
