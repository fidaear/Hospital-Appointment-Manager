package com.GRH.GRH.Repository;

import com.GRH.GRH.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findByPatientId(String patientId);
    List<Patient> findByNameContainingIgnoreCase(String name);
    boolean existsByPatientId(String patientId);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}