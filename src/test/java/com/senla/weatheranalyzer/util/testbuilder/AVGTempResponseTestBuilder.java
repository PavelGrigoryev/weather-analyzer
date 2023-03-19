package com.senla.weatheranalyzer.util.testbuilder;

import com.senla.weatheranalyzer.dto.AVGTempResponse;
import com.senla.weatheranalyzer.util.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aAVGTempResponse")
@With
public class AVGTempResponseTestBuilder implements TestBuilder<AVGTempResponse> {

    private BigDecimal averageTemp = BigDecimal.TEN;

    @Override
    public AVGTempResponse build() {
        return new AVGTempResponse(averageTemp);
    }

}
