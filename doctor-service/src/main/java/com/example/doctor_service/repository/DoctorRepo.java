package com.example.doctor_service.repository;

import com.example.doctor_service.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, String> {
    List<Doctor> findBySpecializationIgnoreCase(String specialiaztion);

    Doctor findByName(String name);
}
