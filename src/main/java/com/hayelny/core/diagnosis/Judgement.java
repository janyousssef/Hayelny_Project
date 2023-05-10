package com.hayelny.core.diagnosis;

import jakarta.persistence.Embeddable;
import lombok.ToString;

@Embeddable
@ToString
public enum Judgement {
    POSITIVE, NEGATIVE;
}
