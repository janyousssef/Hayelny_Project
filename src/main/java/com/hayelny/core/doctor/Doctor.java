package com.hayelny.core.doctor;

import com.hayelny.core.patient.Gender;
import com.hayelny.core.patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Doctor {

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    Set<Patient> patients = new HashSet<>();

    @Id
    @Column(name = "doctor_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", gender=" + gender +
                '}';
    }
}
