package com.hayelny.core.patient;

import com.hayelny.core.diagnosis.Diagnosis;
import com.hayelny.core.doctor.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class Patient {
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @Id
    @Column(name = "patient_id")
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Diagnosis> diagnoses = new HashSet<>();

    public void addDiagnosis(Diagnosis d) {
        diagnoses.add(d);
        d.setPatient(this);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
