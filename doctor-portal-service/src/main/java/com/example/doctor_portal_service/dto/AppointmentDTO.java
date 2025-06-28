package com.example.doctor_portal_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {
    private String id;
    private Long userId;
    private String doctorId;
    private String specialization;
    private LocalDate date;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String id, Long userId, String doctorId, String specialization, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", doctorId='" + doctorId + '\'' +
                ", specialization='" + specialization + '\'' +
                ", date=" + date +
                '}';
    }
}
