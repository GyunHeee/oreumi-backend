package com.oreumi.oreumi_backend.controller.dto;

import com.oreumi.oreumi_backend.domain.review.ReviewStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDetailResponse {
    
    private Long reviewId;
    private String reviewText;
    private ReviewStyle reviewStyle;
    private String generatedReply;
    private LocalDateTime createdAt;
} 