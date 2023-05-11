package com.hayelny.core.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
