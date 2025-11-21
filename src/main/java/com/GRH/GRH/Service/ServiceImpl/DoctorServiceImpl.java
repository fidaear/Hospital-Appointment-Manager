package com.GRH.GRH.Service.ServiceImpl;

import com.GRH.GRH.Service.DoctorService;
import com.GRH.GRH.entity.Doctor;
import com.GRH.GRH.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        // Generate doctor ID if not provided
        if (doctor.getDoctorId() == null) {
            doctor.setDoctorId("D" + System.currentTimeMillis());
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Optional<Doctor> getDoctorByDoctorId(String doctorId) {
        return doctorRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    @Override
    public List<Doctor> searchDoctorsByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Doctor updateDoctor(String id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setName(doctorDetails.getName());
            doctor.setSpecialization(doctorDetails.getSpecialization());
            doctor.setEmail(doctorDetails.getEmail());
            doctor.setPhone(doctorDetails.getPhone());
            doctor.setWorkingDays(doctorDetails.getWorkingDays());
            return doctorRepository.save(doctor);
        }
        return null; // Or throw custom exception
    }

    @Override
    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public boolean existsByDoctorId(String doctorId) {
        return doctorRepository.existsByDoctorId(doctorId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return doctorRepository.existsByEmail(email);
    }
}