package com.hayelny.core.patient;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.EmbeddableInstantiator;

@Embeddable
@ToString
@Getter
@EmbeddableInstantiator(GenderInstantiator.class)
public enum Gender {
    MALE, FEMALE
}
