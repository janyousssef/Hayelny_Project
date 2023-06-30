package com.hayelny.core.diagnosis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeverityDiagnosisResponseAdapter {
    private final RestTemplate httpClient = new RestTemplate();

    Diagnosis getNewDiagnosisFor(String imageId) {
        SeverityDiagnosisDTO dto = httpClient.getForObject("http://localhost:8000/model/severity?id=" + imageId,
                                                                 SeverityDiagnosisDTO.class);

        return createDiagnosisFrom(dto, new Diagnosis());
    }


    Diagnosis createDiagnosisFrom(SeverityDiagnosisDTO dto, Diagnosis diagnosis) {
        log.warn("you are using temporary code at SeverityDiagnosisResponseAdapter.createDiagnosisFrom");
        diagnosis.setConfidence(Math.random());
        diagnosis.setJudgement(Judgement.POSITIVE);
        diagnosis.setDisease(Disease.PNEUMONIA);
        diagnosis.setStatus(DiagnosisStatus.COMPLETED);


        diagnosis.setSeverity(dto.severity());
        diagnosis.setDisease(Disease.PNEUMONIA);
        diagnosis.setStatus(DiagnosisStatus.COMPLETED);
        return diagnosis;
    }
}