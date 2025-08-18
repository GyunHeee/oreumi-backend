package com.oreumi.oreumi_backend.service.history;

import com.oreumi.oreumi_backend.controller.dto.HistoryResponse;
import com.oreumi.oreumi_backend.controller.dto.HistoryCreateRequest;
import com.oreumi.oreumi_backend.domain.history.entity.History;
import com.oreumi.oreumi_backend.domain.history.HistoryType;
import com.oreumi.oreumi_backend.domain.product.entity.Product;
import com.oreumi.oreumi_backend.domain.review.entity.Review;
import com.oreumi.oreumi_backend.repository.history.HistoryRepository;
import com.oreumi.oreumi_backend.repository.product.ProductRepository;
import com.oreumi.oreumi_backend.repository.review.ReviewRepository;
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
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;


    public HistoryResponse getHistoryData() {
        List<History> productHistories = historyRepository.findByHistoryTypeOrderByCreatedAtDesc(HistoryType.PRODUCT);
        List<History> reviewHistories = historyRepository.findByHistoryTypeOrderByCreatedAtDesc(HistoryType.REVIEW);
        
        List<HistoryResponse.ProductHistoryItem> productHistory = productHistories.stream()
                .map(history -> HistoryResponse.ProductHistoryItem.builder()
                        .historyId(history.getHistoryId())
                        .productId(history.getProduct().getProductId())
                        .generatedTitle(history.getProduct().getGeneratedTitle())
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

    @Transactional
    public History createHistory(HistoryCreateRequest request) {
        if (request.getHistoryType() == HistoryType.PRODUCT) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
            
            History history = History.builder()
                    .historyType(request.getHistoryType())
                    .product(product)
                    .build();
            
            return historyRepository.save(history);
        } else if (request.getHistoryType() == HistoryType.REVIEW) {
            Review review = reviewRepository.findById(request.getReviewId())
                    .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));
            
            History history = History.builder()
                    .historyType(request.getHistoryType())
                    .review(review)
                    .build();
            
            return historyRepository.save(history);
        }
        
        throw new IllegalArgumentException("잘못된 히스토리 타입입니다.");
    }
}
