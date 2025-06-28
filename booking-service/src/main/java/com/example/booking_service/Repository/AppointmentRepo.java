package com.example.booking_service.Repository;

import com.example.booking_service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, String> {

    List<Appointment> findByDoctorIdAndDate(String doctorId, LocalDate date);
}
