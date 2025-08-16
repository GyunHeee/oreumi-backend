package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.domain.review.entity.Review;
import com.oreumi.oreumi_backend.dto.common.common.ApiResponse;
import com.oreumi.oreumi_backend.dto.gpt.GptReviewRequest;
import com.oreumi.oreumi_backend.dto.product.ProductResponse;
import com.oreumi.oreumi_backend.dto.review.ReviewResponse;
import com.oreumi.oreumi_backend.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ApiResponse<>(reviewService.findById(id)));
    }
}
