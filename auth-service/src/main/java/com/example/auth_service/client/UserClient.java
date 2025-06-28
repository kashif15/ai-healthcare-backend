package com.example.auth_service.client;

import com.example.auth_service.DTO.AuthRequest;
import com.example.auth_service.DTO.UserDoctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserClient {
    @PostMapping("/api/users/login")
    ResponseEntity<UserDoctor> login(@RequestBody AuthRequest request);
}
