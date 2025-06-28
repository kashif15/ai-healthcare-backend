package com.example.symptom_checker_service.controller;

import com.example.symptom_checker_service.DTO.SymptomRequest;
import com.example.symptom_checker_service.DTO.SymptomResponse;
import com.example.symptom_checker_service.Service.OllamaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/symptoms")
public class SymptomCheckerController {

    private final OllamaService ollamaService;

    public SymptomCheckerController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/check")
    public ResponseEntity<SymptomResponse> checkSymptom(@RequestBody SymptomRequest request) {
        SymptomResponse response = ollamaService.getSuggestion(request.getSymptom());
        return ResponseEntity.ok(response);
    }
}
