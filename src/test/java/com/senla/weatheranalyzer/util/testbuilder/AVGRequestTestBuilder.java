package com.senla.weatheranalyzer.util.testbuilder;

import com.senla.weatheranalyzer.dto.AVGRequest;
import com.senla.weatheranalyzer.util.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aAVGTempRequest")
@With
public class AVGRequestTestBuilder implements TestBuilder<AVGRequest> {

    private LocalDate from = LocalDate.now();
    private LocalDate to = LocalDate.now();

    @Override
    public AVGRequest build() {
        return new AVGRequest(from, to);
    }

}
