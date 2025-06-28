package com.example.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "doctor-service", configuration = FeignConfig.class)
public interface DoctorClient {

    @PostMapping("/api/doctors/book/{doctorId}/{date}")
    ResponseEntity<String> bookSlots(@PathVariable String doctorId, @PathVariable String date);
}

