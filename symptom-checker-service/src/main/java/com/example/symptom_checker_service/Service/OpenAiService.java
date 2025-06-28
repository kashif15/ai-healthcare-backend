//package com.example.symptom_checker_service.Service;
//
//import com.example.symptom_checker_service.DTO.SymptomResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class OpenAiService {
//
//    @Value("${openai.api.key}")
//    private String openAiApiKey;
//
//    public SymptomResponse getSuggestion(String symptom) {
//        String url = "https://api.openai.com/v1/chat/completions";
//
//        // Headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(openAiApiKey);
//
//        // Request Body
//        Map<String, Object> body = new HashMap<>();
//        body.put("model", "gpt-3.5-turbo");
//
//        List<Map<String, String>> messages = List.of(
//                Map.of("role", "system", "content", "You are a helpful medical assistant."),
//                Map.of("role", "user", "content", "I am experiencing: " + symptom)
//        );
//        body.put("messages", messages);
//
//        // Prepare Request
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
//
//        // Call OpenAI API
//        RestTemplate restTemplate = new RestTemplate();
//        Map<?, ?> response = restTemplate.postForObject(url, request, Map.class);
//
//        // Extract message content safely
//        String reply = "Sorry, something went wrong.";
//        try {
//            List<?> choices = (List<?>) response.get("choices");
//            if (choices != null && !choices.isEmpty()) {
//                Map<?, ?> choice = (Map<?, ?>) choices.get(0);
//                Map<?, ?> message = (Map<?, ?>) choice.get("message");
//                reply = (String) message.get("content");
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // log it or handle accordingly
//        }
//
//        // Build response
//        SymptomResponse sr = new SymptomResponse();
//        sr.setSuggestion(reply);
//        return sr;
//    }
//}