package com.example.doctor_portal_service.client;

import com.example.doctor_portal_service.dto.DoctorAvailability;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "doctor-service", configuration = FeignConfig.class)
public interface DoctorClient {
    @PostMapping("/api/doctors/{doctorId}/availability")
    ResponseEntity<DoctorAvailability> addAvailability(
            @PathVariable String doctorId,
            @RequestBody DoctorAvailability availability
    );
}


