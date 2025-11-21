package com.GRH.GRH.Service.ServiceImpl;




import com.GRH.GRH.Service.AppointmentService;
import com.GRH.GRH.entity.Appointment;
import com.GRH.GRH.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private final List<String> availableTimeSlots = List.of(
            "08:00", "09:00", "10:00", "11:00",
            "14:00", "15:00", "16:00", "17:00"
    );

    @Override
    public Appointment createAppointment(Appointment appointment) {
        if (!isTimeSlotAvailable(appointment.getDoctorId(), appointment.getDate(), appointment.getTime())) {
            throw new RuntimeException("This time slot is not available for the selected doctor");
        }

        if (appointment.getAppointmentId() == null) {
            appointment.setAppointmentId("A" + System.currentTimeMillis());
        }

        if (appointment.getStatus() == null) {
            appointment.setStatus("Planifié");
        }

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Optional<Appointment> getAppointmentByAppointmentId(String appointmentId) {
        return appointmentRepository.findByAppointmentId(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByDate(date);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorAndDate(String doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }

    @Override
    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }

    @Override
    public List<String> getAvailableTimeSlots(String doctorId, LocalDate date) {
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorIdAndDate(doctorId, date);
        List<String> bookedSlots = existingAppointments.stream()
                .map(Appointment::getTime)
                .toList();

        List<String> availableSlots = new ArrayList<>();
        for (String slot : availableTimeSlots) {
            if (!bookedSlots.contains(slot)) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }

    @Override
    public Appointment updateAppointment(String id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setPatientId(appointmentDetails.getPatientId());
            appointment.setDoctorId(appointmentDetails.getDoctorId());
            appointment.setDate(appointmentDetails.getDate());
            appointment.setTime(appointmentDetails.getTime());
            appointment.setStatus(appointmentDetails.getStatus());
            appointment.setRemarks(appointmentDetails.getRemarks());
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    @Override
    public Appointment cancelAppointment(String id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus("Annulé");
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    @Override
    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public boolean isTimeSlotAvailable(String doctorId, LocalDate date, String time) {
        return !appointmentRepository.existsByDoctorIdAndDateAndTime(doctorId, date, time);
    }

    @Override
    @Scheduled(cron = "0 0 2 * * ?") // Run daily at 2 AM
    public void updatePastAppointmentsStatus() {
        LocalDate today = LocalDate.now();
        // This will now work after adding the method to repository
        List<Appointment> pastAppointments = appointmentRepository.findByStatusAndDateBefore("Planifié", today);

        for (Appointment appointment : pastAppointments) {
            appointment.setStatus("Terminé");
            appointmentRepository.save(appointment);
        }
    }
}
