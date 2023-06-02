package com.hayelny.core.diagnosis;


import com.hayelny.core.images.ImageRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DiagnosisService {

    private final DiagnosisRepo diagnosisRepo;
    private final ImageRepo imageRepo;
    private final RestTemplate restTemplate = new RestTemplate();

    public DiagnosisService(DiagnosisRepo diagnosisRepo, ImageRepo imageRepo) {
        this.diagnosisRepo = diagnosisRepo;
        this.imageRepo = imageRepo;
    }

    public Diagnosis diagnose(String id) {

        Map responseJson = restTemplate.getForObject("http://localhost:8000/model?id=" + id,
                                                  Map.class);
        DiagnosisResponse response = new DiagnosisResponse(responseJson.get("prediction").toString(),
                                                           responseJson.get("diagnosis").toString());
        return diagnosisRepo.findByImage_Id(id)
                .map(diagnosis -> {
                    diagnosis.setConfidence(response.getConfidence());
                    diagnosis.setJudgement(response.getJudgement());
                    diagnosis.setDisease(Disease.PNEUMONIA);
                    diagnosis.setStatus(DiagnosisStatus.COMPLETED);
                    return diagnosisRepo.save(diagnosis);
                })
                .orElseGet(() -> {
                    Diagnosis diagnosis = new Diagnosis();
                    diagnosis.setImage(imageRepo.getReferenceById(id));
                    diagnosis.setConfidence(response.getConfidence());
                    diagnosis.setJudgement(response.getJudgement());
                    diagnosis.setDisease(Disease.PNEUMONIA);
                    diagnosis.setStatus(DiagnosisStatus.COMPLETED);
                    return diagnosisRepo.save(diagnosis);

                });
    }

}