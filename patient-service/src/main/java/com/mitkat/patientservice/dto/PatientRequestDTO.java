package com.mitkat.patientservice.dto;

import com.mitkat.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class PatientRequestDTO {
    @NotBlank(message = "Name is required")
    @NotNull
    @Size(max = 100, message = "Name must be more than 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    @NotNull(message = "Address cannot be null")
    private String address;

    @NotBlank(message = "Date of Birth is required")
    @NotNull(message = "Date of Birth cannot be null")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered Date cannot be null")
    private String registeredDate;

    public @NotBlank(message = "Name is required") @NotNull @Size(max = 100, message = "Name must be more than 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @NotNull @Size(max = 100, message = "Name must be more than 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @NotNull(message = "Email cannot be null") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @NotNull(message = "Email cannot be null") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Address is required") @NotNull(message = "Address cannot be null") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") @NotNull(message = "Address cannot be null") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Date of Birth is required") @NotNull(message = "Date of Birth cannot be null") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "Date of Birth is required") @NotNull(message = "Date of Birth cannot be null") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered Date cannot be null") String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(@NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered Date cannot be null") String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
