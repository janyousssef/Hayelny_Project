package com.hayelny.core.diagnosis;


import com.hayelny.core.images.ImageRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
public class DiagnosisService {

    private final DiagnosisRepo diagnosisRepo;
    private final ImageRepo imageRepo;
    private final SeverityDiagnosisResponseAdapter severityDiagnosisResponseAdapter;


    public void diagnose(final String imageId) {
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            pool.execute(() -> getDiagnosis(imageId));
        }

    }

    public Diagnosis findByImageId(String id) {
        return diagnosisRepo.findByImage_Id(id)
                .orElseThrow(() -> new EntityNotFoundException("No diagnosis for image with id = " + id + " found."));
    }

    private Diagnosis getDiagnosis(String imageId) {
        Optional<Diagnosis> diagnosisOptional = diagnosisRepo.findByImage_Id(imageId);

        if (diagnosisOptional.isPresent()) {
            return diagnosisOptional.get();
        }

        Diagnosis diagnosis = severityDiagnosisResponseAdapter.getNewDiagnosisFor(imageId);
        diagnosis.setImage(imageRepo.getReferenceById(imageId));
        return diagnosisRepo.save(diagnosis);
    }


}
