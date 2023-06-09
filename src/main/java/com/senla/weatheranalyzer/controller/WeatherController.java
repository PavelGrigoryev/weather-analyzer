package com.senla.weatheranalyzer.controller;

import com.senla.weatheranalyzer.dto.AVGRequest;
import com.senla.weatheranalyzer.dto.AVGResponse;
import com.senla.weatheranalyzer.dto.WeatherDto;
import com.senla.weatheranalyzer.service.WeatherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public Mono<ResponseEntity<WeatherDto>> findCurrentWeather() {
        return weatherService.findCurrentWeather()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<AVGResponse>> findAverageWeather(@Valid @RequestBody AVGRequest request) {
        return weatherService.findAverageWeather(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
