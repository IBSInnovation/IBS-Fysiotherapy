package org.ibs.utils;

import lombok.AllArgsConstructor;
import org.ibs.data.*;
import org.ibs.domain.Physiotherapist;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final CategoryRepository categoryRepository;
    private final ExerciseRepository exerciseRepository;
    private final MeasurementRepository measurementRepository;
    private final PatientRepository patientRepository;
    private final PhysiotherapistRepository physiotherapistRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Physiotherapist physio = new Physiotherapist();
        physio.setEmail("test");
        physiotherapistRepository.save(physio);
        List<Physiotherapist> list = physiotherapistRepository.findAll();
        for (Physiotherapist p : list) {
            System.out.println(p.getEmail());
        }
    }
}
