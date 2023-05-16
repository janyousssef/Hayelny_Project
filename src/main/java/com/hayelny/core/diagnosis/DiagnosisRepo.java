package com.hayelny.core.diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {
    Optional<Diagnosis> findByImage_Id(int imageId);
    Boolean existsByImage_Id(Long imageId);
}
