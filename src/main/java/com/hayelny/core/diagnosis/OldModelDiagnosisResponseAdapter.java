package com.hayelny.core.diagnosis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OldModelDiagnosisResponseAdapter {
    private final RestTemplate httpClient = new RestTemplate();

    Diagnosis getNewDiagnosisFor(String imageId) {
        Map<?, ?> responseJson = httpClient.getForObject("http://localhost:8000/model?id=" + imageId, Map.class);

        assert responseJson != null;
                                                                    //prediction field is the confidence, bad name
        OldDiagnosisResponse diagnosisResponse = new OldDiagnosisResponse(responseJson.get("prediction").toString(),
                                                                          responseJson.get("diagnosis").toString());
        return createDiagnosisFrom(diagnosisResponse);
    }


    Diagnosis createDiagnosisFrom(OldDiagnosisResponse response) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setConfidence(response.getConfidence());
        diagnosis.setJudgement(response.getJudgement());
        diagnosis.setDisease(Disease.PNEUMONIA);
        diagnosis.setStatus(DiagnosisStatus.COMPLETED);
        return diagnosis;
    }
}