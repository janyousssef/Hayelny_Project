package com.hayelny.core.diagnosis;

import jakarta.persistence.Embeddable;
import lombok.ToString;
import org.hibernate.annotations.EmbeddableInstantiator;

@Embeddable
@ToString
@EmbeddableInstantiator(DiagnosisStatusInstantiator.class)
public enum DiagnosisStatus {
    PENDING, COMPLETED
}
