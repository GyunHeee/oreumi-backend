package com.oreumi.oreumi_backend.service.gpt;

import com.oreumi.oreumi_backend.domain.review.ReviewStyle;
import com.oreumi.oreumi_backend.dto.gpt.GptProductResponse;
import com.oreumi.oreumi_backend.dto.gpt.GptReviewResponse;
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
public class GptReviewService {
    private final RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String openaiApiKey;
    @Value("${openai.api.url}")
    private String url;
    @Value("${openai.model}")
    private String model;

    public GptReviewResponse generateReview(String reviewText, ReviewStyle reviewStyle) {
        String apiUrl = url;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        // 프롬포트
        String prompt = "사용자가 남긴 리뷰에 대해 아래 스타일로 답장을 작성해줘.\n" +
                "리뷰: " + reviewText + "\n" +
                "스타일: " + reviewStyle.name() + "\n" +
                "출력 형식: 텍스트로 답장만 제공";

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("messages", List.of(Map.of("role", "user", "content", prompt)));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        List choices = (List) response.getBody().get("choices");
        Map firstChoice = (Map) choices.get(0);
        Map message = (Map) firstChoice.get("message");
        String generatedReply = ((String) message.get("content")).replace("\n", "").trim();

        return new GptReviewResponse(reviewText, reviewStyle, generatedReply);
    }
}
