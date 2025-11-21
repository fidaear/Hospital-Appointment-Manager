package com.GRH.GRH.Repository;

import com.GRH.GRH.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
    Optional<Doctor> findByDoctorId(String doctorId);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByNameContainingIgnoreCase(String name);
    boolean existsByDoctorId(String doctorId);
    boolean existsByEmail(String email);
}