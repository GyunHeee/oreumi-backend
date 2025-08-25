package com.oreumi.oreumi_backend.service.history;

import com.oreumi.oreumi_backend.controller.dto.HistoryResponse;
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

    public HistoryResponse getHistoryData() {
        List<History> productHistories = historyRepository.findProductHistoriesByType(HistoryType.PRODUCT);
        List<History> reviewHistories = historyRepository.findReviewHistoriesByType(HistoryType.REVIEW);
        
        List<HistoryResponse.ProductHistoryItem> productHistory = productHistories.stream()
                .map(history -> HistoryResponse.ProductHistoryItem.builder()
                        .historyId(history.getHistoryId())
                        .productId(history.getProduct().getProductId())
                        .generatedTitle(history.getProduct().getGeneratedTitle())
                        .generatedDescription(history.getProduct().getGeneratedDescription())
                        .createdAt(history.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        List<HistoryResponse.ReviewHistoryItem> reviewHistory = reviewHistories.stream()
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
