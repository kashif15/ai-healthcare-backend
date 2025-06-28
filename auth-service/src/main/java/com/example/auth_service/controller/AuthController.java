package com.example.auth_service.controller;

import com.example.auth_service.DTO.AuthRequest;
import com.example.auth_service.DTO.AuthResponse;
import com.example.auth_service.client.DoctorClient;
import com.example.auth_service.client.UserClient;
import com.example.auth_service.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserClient userClient;
    private final DoctorClient doctorClient;

    public AuthController(JwtUtil jwtUtil, UserClient userClient, DoctorClient doctorClient) {
        this.jwtUtil = jwtUtil;
        this.userClient = userClient;
        this.doctorClient = doctorClient;
    }

    @PostMapping("/user/login")
    public ResponseEntity<AuthResponse> userLogin(@RequestBody AuthRequest request) {
        System.out.println("Calling user-service via Feign for login: " + request.getUsername());

        try {
            var response = userClient.login(request);
            System.out.println("Response from user-service: " + response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                String token = jwtUtil.generateToken(request.getUsername(), "USER");
                return ResponseEntity.ok(new AuthResponse(token));
            } else {
                System.out.println("Login failed with status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Exception while calling user-service:");
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



    @PostMapping("/doctor/login")
    public ResponseEntity<AuthResponse> doctorLogin(@RequestBody AuthRequest request) {
        var response = doctorClient.login(request);
        if (response.getStatusCode().is2xxSuccessful()) {
            String token = jwtUtil.generateToken(request.getUsername(), "DOCTOR");
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
