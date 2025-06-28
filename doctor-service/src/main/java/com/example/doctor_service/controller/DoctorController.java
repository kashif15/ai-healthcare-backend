package com.example.doctor_service.controller;

import com.example.doctor_service.DTO.AuthRequest;
import com.example.doctor_service.DTO.UserDoctor;
import com.example.doctor_service.model.Doctor;
import com.example.doctor_service.model.DoctorAvailability;
import com.example.doctor_service.repository.DoctorAvailabilityRepo;
import com.example.doctor_service.repository.DoctorRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepo doctorRepo;
    private final DoctorAvailabilityRepo doctorAvailabilityRepo;


    public DoctorController(DoctorRepo doctorRepo, DoctorAvailabilityRepo doctorAvailabilityRepo) {
        this.doctorRepo = doctorRepo;
        this.doctorAvailabilityRepo = doctorAvailabilityRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<Doctor> register(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorRepo.save(doctor));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDoctor> login(@RequestBody AuthRequest authRequest){
        Doctor doctor = doctorRepo.findByName(authRequest.getUsername());

        if(doctor != null && doctor.getPassword().equals(authRequest.getPassword())){
            return ResponseEntity.ok(new UserDoctor(doctor.getName(), "DOCTOR"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable String Id){
        return ResponseEntity.of(doctorRepo.findById(Id));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getSpecialization(@RequestParam String specialization) {
        List<Doctor> filteredDoctors = doctorRepo.findBySpecializationIgnoreCase(specialization);
        return ResponseEntity.ok(filteredDoctors);
    }

    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<List<DoctorAvailability>> getAvailability(@PathVariable String doctorId){
        return ResponseEntity.ok(doctorAvailabilityRepo.findByDoctorId(doctorId));
    }

    @PostMapping("/{doctorId}/availability")
    public ResponseEntity<DoctorAvailability> addAvailability(
            @PathVariable String doctorId,
            @RequestBody DoctorAvailability availability
    ) {
        System.out.println("Before setting doctorId/bookedSlots: " + availability);

        availability.setDoctorId(doctorId);
        availability.setBookedSlots(0);

        System.out.println("Before saving: " + availability);

        DoctorAvailability saved = doctorAvailabilityRepo.save(availability);

        System.out.println("After saving: " + saved);

        return ResponseEntity.ok(saved);
    }



    @PostMapping("/book/{doctorId}/{date}")
    public ResponseEntity<String> bookSlots(
            @PathVariable String doctorId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        var optional = doctorAvailabilityRepo.findByDoctorIdAndDate(doctorId, date);;

        if (optional.isPresent()) {
            DoctorAvailability availability = optional.get();

            if (availability.getBookedSlots() < availability.getTotalSlots()) {
                availability.setBookedSlots(availability.getBookedSlots() + 1);
                doctorAvailabilityRepo.save(availability);
                return ResponseEntity.ok("Slot booked.");
            } else {
                return ResponseEntity.badRequest().body("No slots available.");
            }
        }

        return ResponseEntity.notFound().build(); // ✅ Only reached if doctor/date combo not found
    }



}
