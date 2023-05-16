package com.hayelny.core;

import com.hayelny.core.diagnosis.DiagnosisRepo;
import com.hayelny.core.doctor.DoctorRepo;
import com.hayelny.core.patient.PatientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
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
        createImageDirectoryIfMissing();
        setParallelism();
        SpringApplication.run(HayelnyApplication.class,
                              args);
    }

    private static void setParallelism() {
        int availableProcessors = Runtime
                .getRuntime()
                .availableProcessors();
        String desiredParallelism = availableProcessors > 4 ? String.valueOf(availableProcessors) : "4";
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
                           desiredParallelism);
    }

    private static void createImageDirectoryIfMissing() {
        final Path IMAGE_DIRECTORY = Path.of("../images");

        if (Files.notExists(IMAGE_DIRECTORY)) {
            try {
                Files.createDirectory(IMAGE_DIRECTORY);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
        };
    }
}
