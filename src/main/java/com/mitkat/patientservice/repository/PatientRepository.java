package com.mitkat.patientservice.repository;

import com.mitkat.patientservice.model.Patient;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(@NotNull String email);
    boolean existsByEmailAndIdNot(@NotNull String email, @NotNull UUID id);
    boolean existsById(@NotNull UUID id);
}
