package com.GRH.GRH.Service;


import com.GRH.GRH.entity.Appointment;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(String id);
    Optional<Appointment> getAppointmentByAppointmentId(String appointmentId);
    List<Appointment> getAppointmentsByPatientId(String patientId);
    List<Appointment> getAppointmentsByDoctorId(String doctorId);
    List<Appointment> getAppointmentsByDate(LocalDate date);
    List<Appointment> getAppointmentsByDoctorAndDate(String doctorId, LocalDate date);
    List<Appointment> getAppointmentsByStatus(String status);
    List<String> getAvailableTimeSlots(String doctorId, LocalDate date);
    Appointment updateAppointment(String id, Appointment appointment);
    Appointment cancelAppointment(String id);
    void deleteAppointment(String id);
    boolean isTimeSlotAvailable(String doctorId, LocalDate date, String time);
    void updatePastAppointmentsStatus();
}