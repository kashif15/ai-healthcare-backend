package com.example.auth_service.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

