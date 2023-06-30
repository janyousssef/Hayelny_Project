package com.hayelny.core.diagnosis;

import com.hayelny.core.images.XrayImage;
import com.hayelny.core.patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Diagnosis {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id" ,unique = true)
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
    @Enumerated(EnumType.STRING)
    private Disease disease;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    private String severity;

}
