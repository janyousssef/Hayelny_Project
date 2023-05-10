package com.hayelny.core.diagnosis;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Diagnosis {
    @Id
    private Long id;
    private Double confidence;
    @Embedded
    @Enumerated(EnumType.STRING)
    private Judgement judgement;
    private DiagnosisStatus status;

}
