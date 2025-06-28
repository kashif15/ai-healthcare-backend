package com.example.user_service.repository;

import com.example.user_service.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {
    List<Prescription> findByUserId(Long userId);
}
