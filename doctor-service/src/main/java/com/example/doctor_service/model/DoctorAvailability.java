package com.example.doctor_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorId;
    private LocalDate date;
    private int totalSlots;
    private int bookedSlots;

    public DoctorAvailability() {

    }

    public DoctorAvailability(int bookedSlots, int totalSlots, LocalDate date, String doctorId, Long id) {
        this.bookedSlots = bookedSlots;
        this.totalSlots = totalSlots;
        this.date = date;
        this.doctorId = doctorId;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getBookedSlots() {
        return bookedSlots;
    }

    public void setBookedSlots(int bookedSlots) {
        this.bookedSlots = bookedSlots;
    }

    @Override
    public String toString() {
        return "DoctorAvailability{" +
                "id=" + id +
                ", doctorId='" + doctorId + '\'' +
                ", date=" + date +
                ", totalSlots=" + totalSlots +
                ", bookedSlots=" + bookedSlots +
                '}';
    }

}

