package com.GRH.GRH.Repository;

import com.GRH.GRH.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    Optional<Appointment> findByAppointmentId(String appointmentId);
    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctorId(String doctorId);
    List<Appointment> findByDate(LocalDate date);
    List<Appointment> findByDoctorIdAndDate(String doctorId, LocalDate date);
    List<Appointment> findByStatus(String status);

    // ✅ ADD THIS METHOD to fix the error
    List<Appointment> findByStatusAndDateBefore(String status, LocalDate date);

    List<Appointment> findByDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByAppointmentId(String appointmentId);
    boolean existsByDoctorIdAndDateAndTime(String doctorId, LocalDate date, String time);
    List<Appointment> findByPatientIdAndStatus(String patientId, String status);
}