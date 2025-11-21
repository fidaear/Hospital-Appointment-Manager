package com.GRH.GRH.Service;

import com.GRH.GRH.entity.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient createPatient(Patient patient);
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(String id);
    Optional<Patient> getPatientByPatientId(String patientId);
    List<Patient> searchPatientsByName(String name);
    Patient updatePatient(String id, Patient patient);
    void deletePatient(String id);
    boolean existsByPatientId(String patientId);
    boolean existsByEmail(String email);
}