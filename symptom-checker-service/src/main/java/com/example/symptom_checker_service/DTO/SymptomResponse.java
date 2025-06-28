package com.example.symptom_checker_service.DTO;

import lombok.Data;

@Data
public class SymptomResponse {
    private String suggestion;

    public SymptomResponse() {
    }

    public SymptomResponse(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
