//package org.ibs.application.dto.builder;
//
//import lombok.AllArgsConstructor;
//import org.ibs.application.dto.Physiotherapist.PhysiotherapistDTO;
//import org.ibs.application.service.PatientService;
//import org.ibs.domain.Patient;
//import org.ibs.domain.Physiotherapist;
//import org.ibs.utils.DTOMapper;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@AllArgsConstructor
//public class PhysiotherapistDTOMapper implements DTOMapper<PhysiotherapistDTO, Physiotherapist> {
//   private final PatientService patientService;
//
//    @Override
//    public PhysiotherapistDTO toDTO(Physiotherapist o) {
//        return PhysiotherapistDTO.builder()
//                .id(o.getId())
//                .email(o.getEmail())
//                .build();
//    }
//
//    @Override
//    public Physiotherapist fromDTO(PhysiotherapistDTO o) throws Exception {
//        return Physiotherapist.builder()
//                .id(o.id)
//                .email(o.email)
//                .build();
//    }
//}
