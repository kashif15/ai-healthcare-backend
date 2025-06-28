package com.example.symptom_checker_service.Service;

import com.example.symptom_checker_service.DTO.SymptomResponse;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

    private final ChatClient chatClient;

    @Autowired
    public OllamaService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public SymptomResponse getSuggestion(String symptom) {
        String prompt = "You are a medical assistant. A user reports the following symptom: " + symptom +
                ". Suggest possible causes or advice in a concise manner.";

        String reply = chatClient.call(prompt);

        SymptomResponse response = new SymptomResponse();
        response.setSuggestion(reply);
        return response;
    }
}
