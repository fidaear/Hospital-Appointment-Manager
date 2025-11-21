package com.GRH.GRH.Service.ServiceImpl;
import com.GRH.GRH.Service.PatientService;
import com.GRH.GRH.entity.Patient;
import com.GRH.GRH.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService { // ← Add "implements"

    @Autowired
    private PatientRepository patientRepository;

    @Override  // ← Add @Override annotations
    public Patient createPatient(Patient patient) {
        // Add ID generation logic
        if (patient.getPatientId() == null) {
            patient.setPatientId("P" + System.currentTimeMillis());
        }
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    @Override
    public Optional<Patient> getPatientByPatientId(String patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    @Override
    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Patient updatePatient(String id, Patient patientDetails) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setName(patientDetails.getName());
            patient.setDob(patientDetails.getDob());
            patient.setGender(patientDetails.getGender());
            patient.setPhone(patientDetails.getPhone());
            patient.setEmail(patientDetails.getEmail());
            patient.setAddress(patientDetails.getAddress());
            return patientRepository.save(patient);
        }
        return null; // Or throw exception
    }

    @Override
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    @Override
    public boolean existsByPatientId(String patientId) {
        return patientRepository.existsByPatientId(patientId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }
}