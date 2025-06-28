package com.example.symptom_checker_service.DTO;

import lombok.Data;

@Data
public class SymptomRequest {
    private String symptom;

    public SymptomRequest() {
    }

    public SymptomRequest(String symptom) {
        this.symptom = symptom;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
