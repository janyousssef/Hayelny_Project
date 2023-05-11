package com.hayelny.core.diagnosis;

import com.hayelny.core.images.XrayImage;
import com.hayelny.core.patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Diagnosis {
    @OneToOne
    @JoinColumn(name = "image_id")
    private XrayImage image;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Judgement judgement;
    private Double confidence;
    @Enumerated(EnumType.STRING)
    private DiagnosisStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return Objects.equals(id, diagnosis.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
