package com.oreumi.oreumi_backend.service.gpt;

import com.oreumi.oreumi_backend.dto.gpt.GptProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GptProductService {
    private final RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    public GptProductResponse generateProduct(String productInfo) {
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        // 프롬포트
        String prompt = "아래 상품 정보를 기반으로 스토어에 올릴 제목과 상품 설명을 만들어줘.\n" +
                "상품 정보: " + productInfo + "\n" +
                "출력 형식: JSON {\"title\": \"제목\", \"description\": \"설명\"}";

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4o-mini");
        body.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, request, Map.class);

        // JSON parse
        List choices = (List) response.getBody().get("choices");
        Map firstChoice = (Map) choices.get(0);
        Map message = (Map) firstChoice.get("message");
        String gptText = (String) message.get("content");

        gptText = gptText.replace("\n", "");
        String title = gptText.split("\"title\":")[1].split(",")[0].replace("\"", "").trim();
        String description = gptText.split("\"description\":")[1].replace("}", "").replace("\"", "").trim();

        return new GptProductResponse(title, description);
    }
}
