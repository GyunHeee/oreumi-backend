package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.controller.dto.HistoryResponse;
import com.oreumi.oreumi_backend.controller.dto.ProductDetailResponse;
import com.oreumi.oreumi_backend.controller.dto.ReviewDetailResponse;
import com.oreumi.oreumi_backend.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    /**
     * 히스토리 데이터 조회
     * @return 히스토리 응답 데이터
     */
    @GetMapping
    public HistoryResponse getHistoryData() {
        return historyService.getHistoryData();
    }

    /**
     * 상품 상세 정보 조회
     * @param productId 상품 ID
     * @return 상품 상세 정보
     */
    @GetMapping("/products/{productId}")
    public ProductDetailResponse getProductDetail(@PathVariable Long productId) {
        return historyService.getProductDetail(productId);
    }

    /**
     * 리뷰 상세 정보 조회
     * @param reviewId 리뷰 ID
     * @return 리뷰 상세 정보
     */
    @GetMapping("/reviews/{reviewId}")
    public ReviewDetailResponse getReviewDetail(@PathVariable Long reviewId) {
        return historyService.getReviewDetail(reviewId);
    }
}
