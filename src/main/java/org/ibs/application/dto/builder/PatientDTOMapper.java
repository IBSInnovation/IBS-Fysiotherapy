//package org.ibs.application.dto.builder;
//
//import lombok.AllArgsConstructor;
//import org.ibs.application.dto.Patient.PatientDTO;
//import org.ibs.application.service.ExerciseService;
//import org.ibs.application.service.PhysiotherapistService;
//import org.ibs.domain.Exercise;
//import org.ibs.domain.Patient;
//import org.ibs.utils.DTOMapper;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@AllArgsConstructor
//public class PatientDTOMapper implements DTOMapper<PatientDTO, Patient> {
//    private final ExerciseService exerciseService;
//    private final PhysiotherapistService physiotherapistService;
//    @Override
//    public PatientDTO toDTO(Patient o) {
//        return PatientDTO.builder()
//                .id(o.getId())
//                .name(o.getName())
//                .surName(o.getSurName())
//                .weight(o.getWeight())
//                .dateOfBirth(o.getDateOfBirth())
//                .height(o.getHeight())
//                .email(o.getEmail())
//                .physiotherapistId(o.getPhysiotherapist().getId())
//                .exerciseIds(o.getExercises().stream().map(Exercise::getId).collect(Collectors.toList()))
//                .build();
//    }
//
//    @Override
//    public Patient fromDTO(PatientDTO o) throws Exception {
//        List<Exercise> list = new ArrayList<>();
//        for (String exerciseId : o.exerciseIds) {
//            Exercise byId = exerciseService.getById(exerciseId);
//            list.add(byId);
//        }
//        return Patient.builder()
//                .id(o.id)
//                .name(o.name)
//                .surName(o.surName)
//                .weight(o.weight)
//                .dateOfBirth(o.dateOfBirth)
//                .height(o.height)
//                .email(o.email)
//                .physiotherapist(physiotherapistService.getById(o.physiotherapistId))
//                .exercises(list)
//                .build();
//    }
//}
