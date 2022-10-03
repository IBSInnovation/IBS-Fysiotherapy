package org.ibs.utils;

import lombok.AllArgsConstructor;
import org.ibs.data.*;
import org.ibs.domain.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final CategoryRepository categoryRepository;
    private final ExerciseRepository exerciseRepository;
    private final MeasurementRepository measurementRepository;
    private final PatientRepository patientRepository;
    private final PhysiotherapistRepository physiotherapistRepository;


    /**
     * Is run every time the application is started. It creates an object of every domain class and persists it.
     * This means there is always data in the h2 database to test with.
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        persistStarterData();
    }

    /**
     * Fills the database with initial data
     */
    public void persistStarterData() {
        Physiotherapist physiotherapist = Physiotherapist.builder()
                .email("testEmail")
                .build();

        Patient patient = Patient.builder()
                .name("patientName")
                .surName("patientSurName")
                .weight(85.2)
                .dateOfBirth(new Date())
                .height(1.83)
                .email("testEmail")
                .build();

        Measurement measurement = Measurement.builder()
                .dateOfMeasurement(new Date())
                .data(20)
                .build();

        Exercise exercise = Exercise.builder()
                .name("exerciseName")
                .build();

        Category category = Category.builder()
                .name("categoryName")
                .build();

        physiotherapistRepository.save(physiotherapist);
        patientRepository.save(patient);
        measurementRepository.save(measurement);
        exerciseRepository.save(exercise);
        categoryRepository.save(category);

        physiotherapist.addPatient(patient);

        patient.setPhysiotherapist(physiotherapist);
        patient.addExercise(exercise);

        measurement.setExercise(exercise);

        exercise.setPatient(patient);
        exercise.addMeasurement(measurement);
        exercise.setCategory(category);

        category.addExercise(exercise);

        physiotherapistRepository.save(physiotherapist);
        patientRepository.save(patient);
        measurementRepository.save(measurement);
        exerciseRepository.save(exercise);
        categoryRepository.save(category);
    }
}
