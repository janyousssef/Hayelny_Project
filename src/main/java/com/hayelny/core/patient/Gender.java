package com.hayelny.core.patient;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.ToString;

@Embeddable
@ToString
@Getter
public enum Gender {
    MALE, FEMALE
}
