package com.example.doctor_portal_service.controller;

import com.example.doctor_portal_service.client.BookingClient;
import com.example.doctor_portal_service.client.DoctorClient;
import com.example.doctor_portal_service.client.UserClient;
import com.example.doctor_portal_service.dto.AppointmentDTO;
import com.example.doctor_portal_service.dto.DoctorAvailability;
import com.example.doctor_portal_service.dto.PrescriptionDTO;
import com.example.doctor_portal_service.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctor-portal")
public class DoctorPortalController {

    private final BookingClient bookingClient;
    private final UserClient userClient;
    private final DoctorClient doctorClient;
    private final JwtUtil jwtUtil;

    public DoctorPortalController(BookingClient bookingClient, UserClient userClient,
                                  DoctorClient doctorClient, JwtUtil jwtUtil){
        this.bookingClient = bookingClient;
        this.doctorClient = doctorClient;
        this.userClient = userClient;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<AppointmentDTO>> getTodayAppointment(@PathVariable String doctorId){
        List<AppointmentDTO> appointment  = bookingClient.getTodayAppointment(doctorId);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/appointment/{doctorId}/{date}")
    public ResponseEntity<List<AppointmentDTO>> getAppointment(
            @PathVariable String doctorId,
            @PathVariable String date){

        List<AppointmentDTO> appointment  = bookingClient.getAppointment(doctorId,date);

        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/{doctorId}/availability")
    public ResponseEntity<DoctorAvailability> addAvailability(@PathVariable String doctorId,
                                                              @RequestBody DoctorAvailability doctorAvailability){
        return doctorClient.addAvailability(doctorId, doctorAvailability);

    }

    @PostMapping("/{doctorId}/prescribe")
    public ResponseEntity<PrescriptionDTO> addPrescription(
            @PathVariable String doctorId,
            @RequestBody PrescriptionDTO prescriptionDTO,
            HttpServletRequest request
    ) {
        // Extract JWT token from Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            // Extract doctor name from token (assuming it's in `sub`)
            String doctorName = jwtUtil.getUsername(token); // sub is the username/name

            prescriptionDTO.setDoctorName(doctorName);
        }

        prescriptionDTO.setDoctorId(doctorId);
        prescriptionDTO.setDate(LocalDate.now());

        return userClient.addPrescription(prescriptionDTO);
    }



}
