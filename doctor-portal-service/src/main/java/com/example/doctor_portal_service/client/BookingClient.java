package com.example.doctor_portal_service.client;

import com.example.doctor_portal_service.dto.AppointmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name= "booking-service", configuration = FeignConfig.class)
public interface BookingClient {

    @GetMapping("/api/bookings/doctor/{doctorId}/today")
    List<AppointmentDTO> getTodayAppointment(@PathVariable String doctorId);

    @GetMapping("/api/bookings/doctor/{doctorId}/{date}")
    List<AppointmentDTO> getAppointment(@PathVariable String doctorId, @PathVariable String date);


}
