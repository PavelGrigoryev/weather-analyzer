package com.senla.weatheranalyzer.util.testbuilder;

import com.senla.weatheranalyzer.dto.AVGRequest;
import com.senla.weatheranalyzer.util.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aAVGTempRequest")
@With
public class AVGRequestTestBuilder implements TestBuilder<AVGRequest> {

    private String from = "2023-03-18";
    private String to = "2023-03-20";

    @Override
    public AVGRequest build() {
        return new AVGRequest(from, to);
    }

}
