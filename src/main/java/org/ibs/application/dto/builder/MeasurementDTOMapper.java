package org.ibs.application.dto.builder;

import lombok.AllArgsConstructor;
import org.ibs.application.dto.MeasurementDTO;
import org.ibs.application.service.ExerciseService;
import org.ibs.domain.Measurement;
import org.ibs.utils.DTOMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MeasurementDTOMapper implements DTOMapper<MeasurementDTO, Measurement> {
    private final ExerciseService exerciseService;

    @Override
    public MeasurementDTO toDTO(Measurement o) {
        return MeasurementDTO.builder()
                .id(o.getId())
                .dateOfMeasurement(o.getDateOfMeasurement())
                .data(o.getData())
                .exerciseId(o.getExercise().getId())
                .build();
    }

    @Override
    public Measurement fromDTO(MeasurementDTO o) {
        return Measurement.builder()
                .id(o.id)
                .dateOfMeasurement(o.dateOfMeasurement)
                .data(o.data)
                .exercise(exerciseService.getById(o.exerciseId))
                .build();
    }
}
