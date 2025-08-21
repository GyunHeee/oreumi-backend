package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.domain.review.entity.Review;
import com.oreumi.oreumi_backend.dto.common.common.ApiResponse;
import com.oreumi.oreumi_backend.dto.gpt.GptReviewRequest;
import com.oreumi.oreumi_backend.dto.product.ProductResponse;
import com.oreumi.oreumi_backend.dto.review.ReviewResponse;
import com.oreumi.oreumi_backend.service.review.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 생성", description = "GPT API로 리뷰에 대한 응답을 생성합니다.")
    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<ReviewResponse>> generateGptReview(@RequestBody GptReviewRequest request) {
        try {
            ReviewResponse response = reviewService.getGptReview(request);
            return ResponseEntity.ok(new ApiResponse<>(response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage()));
        }
    }

    @Operation(summary = "리뷰 데이터 단 건 조회", description = "리뷰 데이터를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ApiResponse<>(reviewService.findById(id)));
    }
}
