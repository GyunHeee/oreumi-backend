package com.oreumi.oreumi_backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {
    
    private List<ProductHistoryItem> productHistory;
    private List<ReviewHistoryItem> reviewHistory;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductHistoryItem {
        private Long historyId;
        private Long productId;
        private String generatedTitle;
        private LocalDateTime createdAt;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewHistoryItem {
        private Long historyId;
        private Long reviewId;
        private String reviewText;
        private String generatedReply;
        private LocalDateTime createdAt;
    }
} 