package com.example.doctor_portal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.doctor_portal_service.client")
public class DoctorPortalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorPortalServiceApplication.class, args);
	}

}
