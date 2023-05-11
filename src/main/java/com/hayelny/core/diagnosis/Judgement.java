package com.hayelny.core.diagnosis;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.EmbeddableInstantiator;

@Embeddable
@Getter
@EmbeddableInstantiator(JudgementInstantiator.class)
public enum Judgement {
    POSITIVE, NEGATIVE
}
