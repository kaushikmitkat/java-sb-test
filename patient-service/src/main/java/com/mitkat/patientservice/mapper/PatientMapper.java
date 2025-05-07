package com.mitkat.patientservice.mapper;

import com.mitkat.patientservice.dto.PatientRequestDTO;
import com.mitkat.patientservice.dto.PatientResponseDTO;
import com.mitkat.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDto(Patient patient) {
        if (patient == null) {
            return null;
        }
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId().toString());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setAddress(patient.getAddress());
        dto.setDateOfBirth(patient.getDateOfBirth().toString());
        return dto;
    }

    public static Patient toEntity(PatientRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setAddress(dto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(dto.getRegisteredDate()));
        return patient;
    }

}
