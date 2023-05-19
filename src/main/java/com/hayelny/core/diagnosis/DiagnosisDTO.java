package com.hayelny.core.diagnosis;

import java.util.Objects;

public class DiagnosisDTO {
    private final Long imageId;
    private final Long patientId;
    private final Long diagnosisId;
    private final String disease;
    private final String judgement;
    private final String confidence;
    private final String diagnosisStatus;

    public DiagnosisDTO(Long imageId, Long patientId, Long diagnosisId, String disease, String judgement, String confidence,
                        String diagnosisStatus) {
        this.imageId = imageId;
        this.patientId = patientId;
        this.diagnosisId = diagnosisId;
        this.disease = disease;
        this.judgement = judgement;
        this.confidence = confidence;
        this.diagnosisStatus = diagnosisStatus;
    }

    public static DiagnosisDTO from(Diagnosis diagnosis) {
        return new DiagnosisDTO(diagnosis
                                        .getImage()
                                        .getId(),
                                diagnosis.getPatient() == null ? null : diagnosis
                                        .getPatient()
                                        .getId(),
                                diagnosis.getId(),
                                diagnosis.getDisease() == null ? null : diagnosis
                                        .getDisease()
                                        .name(),
                                diagnosis.getJudgement() == null ? null : diagnosis
                                        .getJudgement()
                                        .name(),
                                getConfidence(diagnosis),
                                diagnosis.getStatus() == null ? null : diagnosis
                                        .getStatus()
                                        .name());
    }

    private static String getConfidence(Diagnosis diagnosis) {
        Double confidence = diagnosis.getConfidence() == null ? null : diagnosis.getConfidence();

        if (diagnosis.getJudgement().equals(Judgement.NEGATIVE)) confidence = 100 - confidence;
        return confidence.toString();
    }

    public Long getImageId() {
        return imageId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public String getDisease() {
        return disease;
    }

    public String getJudgement() {
        return judgement;
    }

    public String getConfidence() {
        return confidence;
    }

    public String getDiagnosisStatus() {
        return diagnosisStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DiagnosisDTO) obj;
        return Objects.equals(this.imageId,
                              that.imageId) &&
                Objects.equals(this.patientId,
                               that.patientId) &&
                Objects.equals(this.diagnosisId,
                               that.diagnosisId) &&
                Objects.equals(this.disease,
                               that.disease) &&
                Objects.equals(this.judgement,
                               that.judgement) &&
                Objects.equals(this.confidence,
                               that.confidence) &&
                Objects.equals(this.diagnosisStatus,
                               that.diagnosisStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId,
                            patientId,
                            diagnosisId,
                            disease,
                            judgement,
                            confidence,
                            diagnosisStatus);
    }

    @Override
    public String toString() {
        return "DiagnosisDTO[" +
                "imageId=" + imageId + ", " +
                "patientId=" + patientId + ", " +
                "diagnosisId=" + diagnosisId + ", " +
                "disease=" + disease + ", " +
                "judgement=" + judgement + ", " +
                "confidence=" + confidence + ", " +
                "diagnosisStatus=" + diagnosisStatus + ']';
    }

}
