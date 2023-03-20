package com.senla.weatheranalyzer.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.weatheranalyzer.model.Weather;
import com.senla.weatheranalyzer.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {

    private final WebClient webClient;
    private final WeatherRepository weatherRepository;

    /**
     * Method for tracking weather data using an API.
     * Called at fixed time using the @Scheduled annotation.
     */
    @Scheduled(fixedRateString = "${weather.update.rate}")
    private void trackWeather() {
        webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .mapNotNull(s -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        return objectMapper.readTree(s);
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage(), e);
                    }
                    return null;
                })
                .map(WeatherScheduler::createWeather)
                .flatMap(weatherRepository::save)
                .onErrorResume(e -> {
                    log.error("Error while trackingWeather: {}", e.getMessage());
                    return Mono.empty();
                })
                .log("WeatherScheduler trackWeather")
                .subscribe();
    }

    /**
     * Method for creating a Weather object from a JSON string received from an API.
     *
     * @param jsonNode a JsonNode object containing weather data.
     * @return a Weather object representing the weather data.
     */
    private static Weather createWeather(JsonNode jsonNode) {
        String temperature = jsonNode.at("/current/temp_c").asText();
        String windSpeed = jsonNode.at("/current/wind_mph").asText();
        String pressure = jsonNode.at("/current/pressure_mb").asText();
        String humidity = jsonNode.at("/current/humidity").asText();
        String condition = jsonNode.at("/current/condition/text").asText();
        String location = jsonNode.at("/location/name").asText();
        return Weather.builder()
                .temperature(Double.valueOf(temperature))
                .windSpeed(Double.valueOf(windSpeed))
                .pressure(Double.valueOf(pressure))
                .humidity(Integer.valueOf(humidity))
                .condition(condition)
                .location(location)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
    }

}
