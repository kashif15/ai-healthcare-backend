package com.example.user_service.controller;

import com.example.user_service.DTO.AuthRequest;
import com.example.user_service.DTO.UserDoctor;
import com.example.user_service.model.Appointment;
import com.example.user_service.model.Prescription;
import com.example.user_service.model.User;
import com.example.user_service.repository.AppointmentRepo;
import com.example.user_service.repository.PrescriptionRepo;
import com.example.user_service.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepo userRepo;
    private final PrescriptionRepo prescriptionRepo;
    private final AppointmentRepo appointmentRepo;


    public UserController(UserRepo userRepo, PrescriptionRepo prescriptionRepo, AppointmentRepo appointmentRepo) {
        this.userRepo = userRepo;
        this.prescriptionRepo = prescriptionRepo;
        this.appointmentRepo = appointmentRepo;
    }

    @PostMapping("/register")
    public  ResponseEntity<User> register(@RequestBody User user){
        return  ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDoctor> login(@RequestBody AuthRequest request) {
        User user = userRepo.findByName(request.getUsername());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.ok(new UserDoctor(user.getName(), "USER"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.of(userRepo.findById(id));
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment){
        return  ResponseEntity.ok(appointmentRepo.save(appointment));
    }

    @PostMapping("/prescriptions")
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription){
        return ResponseEntity.ok(prescriptionRepo.save(prescription));
    }

    @GetMapping("/{userId}/appointments")
    public ResponseEntity<List<Appointment>> getAppointment(@PathVariable Long userId){
        return ResponseEntity.ok(appointmentRepo.findByUserId(userId));
    }

    @GetMapping("/{userId}/prescriptions")
    public ResponseEntity<List<Prescription>> getPrescription(@PathVariable Long userId){
        return ResponseEntity.ok(prescriptionRepo.findByUserId(userId));
    }
}
