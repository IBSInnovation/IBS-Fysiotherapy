package org.ibs.application.dto.builder;

import lombok.AllArgsConstructor;
import org.ibs.application.dto.ExerciseDTO;
import org.ibs.application.service.MeasurementService;
import org.ibs.application.service.PatientService;
import org.ibs.domain.Exercise;
import org.ibs.domain.Measurement;
import org.ibs.utils.DTOMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ExerciseDTOMapper implements DTOMapper<ExerciseDTO, Exercise> {
    private final PatientService patientService;
    private final MeasurementService measurementService;

    @Override
    public ExerciseDTO toDTO(Exercise o) {
        return ExerciseDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .patientId(o.getPatient().getId())
                .measurementIds(o.getMeasurements().stream().map(Measurement::getId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Exercise fromDTO(ExerciseDTO o) throws Exception {
        List<Measurement> list = new ArrayList<>();
        for (Long measurementId : o.measurementIds) {
            Measurement byId = measurementService.getById(measurementId);
            list.add(byId);
        }
        return Exercise.builder()
                .id(o.id)
                .name(o.name)
                .patient(patientService.getById(o.patientId))
                .measurements(list)
                .build();
    }
}
