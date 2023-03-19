package com.senla.weatheranalyzer.util.testbuilder;

import com.senla.weatheranalyzer.dto.AVGTempResponse;
import com.senla.weatheranalyzer.util.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aAVGTempResponse")
@With
public class AVGTempResponseTestBuilder implements TestBuilder<AVGTempResponse> {

    private Double averageTemp = 10.0;
    private Double averageWindSpeed = 8.2;
    private Double averagePressure = 1020.25;
    private Double averageHumidity = 64.5;
    private String location = "Minsk";

    @Override
    public AVGTempResponse build() {
        return new AVGTempResponse(
                averageTemp,
                averageWindSpeed,
                averagePressure,
                averageHumidity,
                location
        );
    }

}
