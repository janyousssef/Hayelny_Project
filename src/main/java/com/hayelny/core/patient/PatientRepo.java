package com.hayelny.core.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    //function to find patients and their diagnoses
    @Query("SELECT p FROM Patient p left JOIN FETCH p.diagnoses WHERE p.id = ?1")
    Patient findPatientWithDiagnoses(Long id);

    Set<Patient> findPatientsByDoctorId(Long doctorId);
}
