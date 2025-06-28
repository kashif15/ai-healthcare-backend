package com.example.auth_service.client;

import com.example.auth_service.DTO.AuthRequest;
import com.example.auth_service.DTO.UserDoctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("doctor-service")
public interface DoctorClient {
    @PostMapping("/api/doctors/login")
    ResponseEntity<UserDoctor> login(@RequestBody AuthRequest request);
}