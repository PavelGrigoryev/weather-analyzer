package com.senla.weatheranalyzer.mapper;

import com.senla.weatheranalyzer.dto.WeatherDto;
import com.senla.weatheranalyzer.model.Weather;
import org.mapstruct.Mapper;

@Mapper
public interface WeatherMapper {

    WeatherDto toDto(Weather weather);

}
