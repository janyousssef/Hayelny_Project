package com.hayelny.core.diagnosis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiagnosisResponseAdapter {
    private final RestTemplate restTemplate = new RestTemplate();

    Diagnosis getNewDiagnosisFor(String imageId) {
        Map<?, ?> responseJson = restTemplate.getForObject("http://localhost:8000/model?id=" + imageId, Map.class);

        assert responseJson != null;
                                                                    //prediction field is the confidence, bad name
        DiagnosisResponse diagnosisResponse = new DiagnosisResponse(responseJson.get("prediction").toString(),
                                                                    responseJson.get("diagnosis").toString());
        return createDiagnosisFrom(diagnosisResponse);
    }


    Diagnosis createDiagnosisFrom(DiagnosisResponse response) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setConfidence(response.getConfidence());
        diagnosis.setJudgement(response.getJudgement());
        diagnosis.setDisease(Disease.PNEUMONIA);
        diagnosis.setStatus(DiagnosisStatus.COMPLETED);
        return diagnosis;
    }
}