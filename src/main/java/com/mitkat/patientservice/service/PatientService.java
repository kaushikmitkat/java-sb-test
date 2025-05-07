package com.mitkat.patientservice.service;

import com.mitkat.patientservice.dto.PatientRequestDTO;
import com.mitkat.patientservice.dto.PatientResponseDTO;
import com.mitkat.patientservice.exception.EmailAlreadyExistsException;
import com.mitkat.patientservice.exception.PatientDoesNotExistException;
import com.mitkat.patientservice.mapper.PatientMapper;
import com.mitkat.patientservice.model.Patient;
import com.mitkat.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        try {
            List<Patient> patients = patientRepository.findAll();
            if (patients.isEmpty()) {
                return List.of();
            }
            return patients.stream()
                    .map(PatientMapper::toDto)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        try {
            // Check if patient email is already registered
            if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists");
            }

            Patient patient = PatientMapper.toEntity(patientRequestDTO);
            Patient savedPatient = patientRepository.save(patient);
            return PatientMapper.toDto(savedPatient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {

        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientDoesNotExistException("Patient not found: {}", id));

        // Check if email is already registered by another patient
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email already exists: - " + patientRequestDTO.getEmail());
        }
        // Implement the update logic here
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        // Save the updated patient
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }

    public void deletePatient(UUID id) {
        // Implement the delete logic here
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientDoesNotExistException("Patient not found: {}", id));
        patientRepository.delete(patient);
    }
}
