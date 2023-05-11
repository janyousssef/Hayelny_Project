package com.hayelny.core.doctor;

import com.hayelny.core.patient.Patient;
import com.hayelny.core.patient.PatientRepo;
import jakarta.persistence.EntityNotFoundException;

public class DoctorService {
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    public DoctorService(PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public Doctor persist(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    public Doctor getDoctor(Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No doctor with id = " + id + " found.")
                );
    }

    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    public void deleteDoctor(Doctor doctor) {
        doctorRepo.deleteById(doctor.getId());
    }


    /**
     * @param doctor  the id of the doctor to add the patient to
     * @param patient the id of the patient to add to the doctor
     * @description Add patient to doctor
     * @note: Both doctor and patient have to have been persisted before
     */
    public void addPatient(Doctor doctor, Patient patient) {
        patient.setDoctor(doctor);
        patientRepo.save(patient);
    }


}

