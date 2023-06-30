package com.hayelny.core.diagnosis;

import lombok.Data;

@Data
public class OldDiagnosisResponse {
    private Double confidence;
    private Judgement judgement;

    public OldDiagnosisResponse(String confidence, String judgement) {
        this.confidence = Double.parseDouble(confidence);
        this.judgement = judgement.equals("negative") ? Judgement.NEGATIVE : Judgement.POSITIVE;
    }
}
