package com.mitkat.patientservice.exception;

import java.util.UUID;

public class PatientDoesNotExistException extends RuntimeException {
    public PatientDoesNotExistException(String message, UUID id) {
        super(message);
    }
}
