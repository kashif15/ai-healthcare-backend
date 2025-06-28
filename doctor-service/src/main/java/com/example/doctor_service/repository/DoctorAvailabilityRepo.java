package com.example.doctor_service.repository;

import com.example.doctor_service.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DoctorAvailabilityRepo extends JpaRepository<DoctorAvailability, Long> {

    Optional<DoctorAvailability> findByDoctorIdAndDate(String doctorId, LocalDate date);

    List<DoctorAvailability> findByDoctorId(String DoctorId);
}
