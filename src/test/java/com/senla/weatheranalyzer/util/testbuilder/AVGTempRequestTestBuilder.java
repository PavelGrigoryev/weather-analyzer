package com.senla.weatheranalyzer.util.testbuilder;

import com.senla.weatheranalyzer.dto.AVGTempRequest;
import com.senla.weatheranalyzer.util.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aAVGTempRequest")
@With
public class AVGTempRequestTestBuilder implements TestBuilder<AVGTempRequest> {

    private LocalDate from = LocalDate.now();
    private LocalDate to = LocalDate.now();

    @Override
    public AVGTempRequest build() {
        return new AVGTempRequest(from, to);
    }

}
