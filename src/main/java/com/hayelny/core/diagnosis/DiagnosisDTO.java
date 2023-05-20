package com.hayelny.core.diagnosis;

import lombok.Data;

@Data
public class DiagnosisDTO {
    private final String imageId;
    private final Long patientId;
    private final Long diagnosisId;
    private final String disease;
    private final String judgement;
    private final String confidence;
    private final String diagnosisStatus;

    public DiagnosisDTO(String imageId, Long patientId, Long diagnosisId, String disease, String judgement, String confidence, String diagnosisStatus) {
        this.imageId = imageId;
        this.patientId = patientId;
        this.diagnosisId = diagnosisId;
        this.disease = disease;
        this.judgement = judgement;
        this.confidence = confidence;
        this.diagnosisStatus = diagnosisStatus;
    }

    public static DiagnosisDTO from(Diagnosis diagnosis) {
        return new DiagnosisDTO(diagnosis.getImage()
                                        .getId(),
                                diagnosis.getPatient() == null ? null : diagnosis.getPatient()
                                        .getId(),
                                diagnosis.getId(),
                                diagnosis.getDisease() == null ? null : diagnosis.getDisease()
                                        .name(),
                                diagnosis.getJudgement() == null ? null : diagnosis.getJudgement()
                                        .name(),
                                getConfidence(diagnosis),
                                diagnosis.getStatus() == null ? null : diagnosis.getStatus()
                                        .name());
    }


    private static String getConfidence(Diagnosis diagnosis) {
        Double confidence = diagnosis.getConfidence() == null ? null : diagnosis.getConfidence();

        if (diagnosis.getJudgement()
                .equals(Judgement.NEGATIVE)) confidence = 100 - confidence;
        return confidence.toString();
    }

}
