package com.hayelny.core;

import com.hayelny.core.diagnosis.Diagnosis;
import com.hayelny.core.diagnosis.DiagnosisRepo;
import com.hayelny.core.diagnosis.DiagnosisStatus;
import com.hayelny.core.diagnosis.Judgement;
import com.hayelny.core.patient.Gender;
import com.hayelny.core.patient.Patient;
import com.hayelny.core.patient.PatientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class HayelnyApplication {

    final DiagnosisRepo diagnosisRepo;
    final PatientRepo patientRepo;

    public HayelnyApplication(DiagnosisRepo diagnosisRepo, PatientRepo patientRepo) {
        this.diagnosisRepo = diagnosisRepo;
        this.patientRepo = patientRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(HayelnyApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            var p = new Patient();
            p.setFirstName("new Ahmed");
            p.setLastName("new Mohamed");
            p.setDob(LocalDate.now());
            p.setGender(Gender.MALE);

            var d = new Diagnosis();
            d.setJudgement(Judgement.POSITIVE);
            d.setStatus(DiagnosisStatus.COMPLETED);
            d.setConfidence(90D);
            d.setPatient(p);
            p.addDiagnosis(d);
            patientRepo.save(p);
            diagnosisRepo.save(d);

            var list = patientRepo.findAll();
            var saved = list.get(list.size()-1);
            System.out.println(p);
            System.out.println(saved);

        };
    }
}
