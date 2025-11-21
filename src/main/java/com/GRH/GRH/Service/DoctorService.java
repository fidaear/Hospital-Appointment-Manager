package com.GRH.GRH.Service;



import com.GRH.GRH.entity.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor createDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(String id);
    Optional<Doctor> getDoctorByDoctorId(String doctorId);
    List<Doctor> getDoctorsBySpecialization(String specialization);
    List<Doctor> searchDoctorsByName(String name);
    Doctor updateDoctor(String id, Doctor doctor);
    void deleteDoctor(String id);
    boolean existsByDoctorId(String doctorId);
    boolean existsByEmail(String email);
}
