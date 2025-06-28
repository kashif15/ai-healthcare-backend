package com.example.doctor_portal_service.client;

import com.example.doctor_portal_service.dto.PrescriptionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface UserClient {

    @PostMapping("/api/users/prescriptions")
    ResponseEntity<PrescriptionDTO> addPrescription(@RequestBody PrescriptionDTO prescriptionDTO);
}
