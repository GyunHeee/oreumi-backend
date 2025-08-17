package com.oreumi.oreumi_backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {
    
    private Long productId;
    private String inputText;
    private String generatedTitle;
    private String generatedDescription;
    private LocalDateTime createdAt;
} 