package com.example.doctor_service.DTO;

import lombok.Data;

@Data
public class UserDoctor {
    private String username;
    private String role;

    public UserDoctor() {
    }

    public UserDoctor(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
