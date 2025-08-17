package com.oreumi.oreumi_backend.service.history;

import com.oreumi.oreumi_backend.controller.dto.HistoryResponse;
import com.oreumi.oreumi_backend.controller.dto.ProductDetailResponse;
import com.oreumi.oreumi_backend.controller.dto.ReviewDetailResponse;
import com.oreumi.oreumi_backend.domain.history.entity.History;
import com.oreumi.oreumi_backend.domain.history.HistoryType;
import com.oreumi.oreumi_backend.repository.history.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService {

    private final HistoryRepository historyRepository;

    /**
     * 히스토리 데이터 조회
     * @return 히스토리 응답 데이터
     */
    public HistoryResponse getHistoryData() {
        // 모든 히스토리 조회
        List<History> allHistories = historyRepository.findAllByOrderByCreatedAtDesc();
        
        // 상품 히스토리 필터링 및 변환
        List<HistoryResponse.ProductHistoryItem> productHistory = allHistories.stream()
                .filter(history -> history.getHistoryType() == HistoryType.PRODUCT)
                .map(history -> HistoryResponse.ProductHistoryItem.builder()
                        .historyId(history.getHistoryId())
                        .productId(history.getProduct().getProductId())
                        .generatedTitle(history.getProduct().getGeneratedTitle())
                        .createdAt(history.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        // 리뷰 히스토리 필터링 및 변환
        List<HistoryResponse.ReviewHistoryItem> reviewHistory = allHistories.stream()
                .filter(history -> history.getHistoryType() == HistoryType.REVIEW)
                .map(history -> HistoryResponse.ReviewHistoryItem.builder()
                        .historyId(history.getHistoryId())
                        .reviewId(history.getReview().getReviewId())
                        .reviewText(history.getReview().getReviewText())
                        .generatedReply(history.getReview().getGeneratedReply())
                        .createdAt(history.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return HistoryResponse.builder()
                .productHistory(productHistory)
                .reviewHistory(reviewHistory)
                .build();
    }

    
}
