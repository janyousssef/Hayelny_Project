package com.hayelny.core.diagnosis;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/diagnosis")
public class DiagnosisController {
    private final DiagnosisRepo diagnosisRepo;

    public DiagnosisController(DiagnosisRepo diagnosisRepo) {
        this.diagnosisRepo = diagnosisRepo;
    }

    @GetMapping(value = "/{id}")
    public DiagnosisDTO getDiagnosis(@PathVariable Long id) {
        Diagnosis diagnosis = diagnosisRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No diagnosis for image with id = " + id + " found."));

        return DiagnosisDTO.from(diagnosis);
    }
}
