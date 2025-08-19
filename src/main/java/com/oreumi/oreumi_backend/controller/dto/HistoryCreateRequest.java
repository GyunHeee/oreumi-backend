package com.oreumi.oreumi_backend.controller.dto;

import com.oreumi.oreumi_backend.domain.history.HistoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCreateRequest {
    
    private HistoryType historyType;
    private Long productId;  // 상품 히스토리인 경우
    private Long reviewId;   // 리뷰 히스토리인 경우
} 