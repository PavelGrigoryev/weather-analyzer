package com.senla.weatheranalyzer.controller;

import com.senla.weatheranalyzer.dto.AVGTempRequest;
import com.senla.weatheranalyzer.dto.AVGTempResponse;
import com.senla.weatheranalyzer.dto.WeatherDto;
import com.senla.weatheranalyzer.mapper.WeatherMapper;
import com.senla.weatheranalyzer.service.WeatherService;
import com.senla.weatheranalyzer.util.testbuilder.AVGTempRequestTestBuilder;
import com.senla.weatheranalyzer.util.testbuilder.AVGTempResponseTestBuilder;
import com.senla.weatheranalyzer.util.testbuilder.WeatherTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.doReturn;

@WebFluxTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private WeatherService weatherService;
    private final WeatherMapper weatherMapper = Mappers.getMapper(WeatherMapper.class);

    @Test
    @DisplayName("check GET endpoint should return status Ok and expected body")
    void checkGetEndpointShouldReturnStatusOkAndExpectedBody() {
        WeatherDto weatherDto = weatherMapper.toDto(WeatherTestBuilder.aWeather().build());

        doReturn(Mono.just(weatherDto))
                .when(weatherService)
                .findCurrentWeather();

        webTestClient.get()
                .uri("/weather")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WeatherDto.class)
                .isEqualTo(weatherDto);
    }

    @Test
    @DisplayName("check GET endpoint should return status Not Found")
    void checkGetEndpointShouldReturnStatusNotFound() {
        doReturn(Mono.empty())
                .when(weatherService)
                .findCurrentWeather();

        webTestClient.get()
                .uri("/weather")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("check POST endpoint should return status Ok and expected body")
    void checkPostEndpointShouldReturnStatusOkAndExpectedBody() {
        AVGTempRequest request = AVGTempRequestTestBuilder.aAVGTempRequest().build();
        AVGTempResponse response = AVGTempResponseTestBuilder.aAVGTempResponse().build();

        doReturn(Mono.just(response))
                .when(weatherService)
                .findAverageWeather(request);

        webTestClient.post()
                .uri("/weather")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AVGTempResponse.class)
                .isEqualTo(response);
    }

    @Test
    @DisplayName("check POST endpoint should return status Not Found")
    void checkPostEndpointShouldReturnStatusNotFound() {
        AVGTempRequest request = AVGTempRequestTestBuilder.aAVGTempRequest().build();

        doReturn(Mono.empty())
                .when(weatherService)
                .findAverageWeather(request);

        webTestClient.post()
                .uri("/weather")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

}
