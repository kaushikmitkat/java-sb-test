package com.mitkat.patientservice.controller;

import com.mitkat.patientservice.dto.PatientRequestDTO;
import com.mitkat.patientservice.dto.PatientResponseDTO;
import com.mitkat.patientservice.dto.validators.CreatePatientValidationGroup;
import com.mitkat.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {
    // This class will handle HTTP requests related to patients
    // It will use the PatientService to perform operations on patients
    // For example, it can handle requests to get a list of patients, add a new patient, etc.
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

     @GetMapping("")
     @Operation(summary = "Get all patients", description = "Retrieve a list of all patients")
     public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
         return ResponseEntity.ok(patientService.getPatients());
     }

    @PostMapping("")
    @Operation(summary = "Create a new patient", description = "Add a new patient to the system")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        try {
            return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
        } catch (Exception e) {
            // Handle the exception and return an appropriate response
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patient", description = "Update the details of an existing patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        // Implement the update logic here
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Remove a patient from the system")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        // Implement the delete logic here
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
