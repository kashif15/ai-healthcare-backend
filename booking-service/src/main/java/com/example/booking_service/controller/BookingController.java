package com.example.booking_service.controller;

import com.example.booking_service.Repository.AppointmentRepo;
import com.example.booking_service.client.DoctorClient;
import com.example.booking_service.client.UserClient;
import com.example.booking_service.model.Appointment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final AppointmentRepo appointmentRepo;
    private final DoctorClient doctorClient;
    private final UserClient userClient;


    public BookingController(AppointmentRepo appointmentRepo, DoctorClient doctorClient, UserClient userClient) {
        this.appointmentRepo = appointmentRepo;
        this.doctorClient = doctorClient;
        this.userClient = userClient;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment){

        appointment.setId(UUID.randomUUID().toString());

        ResponseEntity<String> doctorResponse = doctorClient.bookSlots(appointment.getDoctorId(),
                appointment.getDate().toString());

        if(!doctorResponse.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Doctors booking slot failed: " + doctorResponse.getBody());

        }

        appointmentRepo.save(appointment);

        userClient.addAppointments(appointment);

        return ResponseEntity.ok("Appointment booked successfully");

    }

    @GetMapping("/doctor/{doctorId}/today")
    public ResponseEntity<List<Appointment>> getTodayAppointment(@PathVariable String doctorId){
        LocalDate today = LocalDate.now();
        return ResponseEntity.ok(appointmentRepo.findByDoctorIdAndDate(doctorId,today));
    }

    @GetMapping("/doctor/{doctorId}/{date}")
    public ResponseEntity<List<Appointment>> getAppointment(
            @PathVariable String doctorId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){

        return ResponseEntity.ok(appointmentRepo.findByDoctorIdAndDate(doctorId,date));
    }

}
