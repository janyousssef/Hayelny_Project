package com.hayelny.core.diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {
}
