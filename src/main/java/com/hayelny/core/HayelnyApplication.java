package com.hayelny.core;

import com.hayelny.core.diagnosis.DiagnosisRepo;
import com.hayelny.core.doctor.DoctorRepo;
import com.hayelny.core.patient.PatientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
@ServletComponentScan
public class HayelnyApplication {

    final DiagnosisRepo diagnosisRepo;
    final PatientRepo patientRepo;
    final DoctorRepo doctorRepo;

    public HayelnyApplication(DiagnosisRepo diagnosisRepo, PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.diagnosisRepo = diagnosisRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(HayelnyApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            final Path IMAGE_DIRECTORY = Path.of("../images");

            if (Files.notExists(IMAGE_DIRECTORY)) {
                Files.createDirectory(IMAGE_DIRECTORY);
            }
        };
    }
}
