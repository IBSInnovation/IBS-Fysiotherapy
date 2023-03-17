package org.ibs.application.dto.exercisedto;

import io.opencensus.stats.Measure;
import lombok.NoArgsConstructor;
import org.ibs.domain.Measurement;
import org.ibs.utils.DTO;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class GetExercise extends DTO {
    public String id;
    public String name;
    public List<Measurement> measurements;

    public GetExercise(String id, String name, List<Measurement> measurements) {
        this.id = id;
        this.name = name;
        this.measurements = measurements;
    }
}
