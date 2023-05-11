package com.hayelny.core.diagnosis;

public record DiagnosisDTO(Long imageId, Long patientId, Long diagnosisId,String disease, String judgement, String confidence,
                           String status) {
    public static DiagnosisDTO from(Diagnosis diagnosis) {
        return new DiagnosisDTO(diagnosis.getImage().getId(),
                                diagnosis.getPatient() == null ? null : diagnosis.getPatient().getId(),
                                diagnosis.getId(),
                                diagnosis.getDisease()== null ? null : diagnosis.getDisease().name(),
                                diagnosis.getJudgement() == null ? null : diagnosis.getJudgement().name(),
                                diagnosis.getConfidence() == null ? null : diagnosis.getConfidence().toString(),
                                diagnosis.getStatus() == null ? null : diagnosis.getStatus().name());
    }
}
