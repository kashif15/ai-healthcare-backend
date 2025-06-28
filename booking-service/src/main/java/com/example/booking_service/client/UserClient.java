package com.example.booking_service.client;

import com.example.booking_service.model.Appointment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", configuration = FeignConfig.class)
public interface UserClient {

    @PostMapping("/api/users/appointments")
    ResponseEntity<?> addAppointments(@RequestBody Appointment appointment);
}
