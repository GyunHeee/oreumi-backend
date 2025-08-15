package com.oreumi.oreumi_backend.service.review;

import com.oreumi.oreumi_backend.domain.review.entity.Review;
import com.oreumi.oreumi_backend.dto.gpt.GptReviewRequest;
import com.oreumi.oreumi_backend.dto.gpt.GptReviewResponse;
import com.oreumi.oreumi_backend.dto.review.ReviewResponse;
import com.oreumi.oreumi_backend.repository.review.ReviewRepository;
import com.oreumi.oreumi_backend.service.gpt.GptReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final GptReviewService gptReviewService;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponse getGptReview(GptReviewRequest request) {
        GptReviewResponse response = gptReviewService.generateReview(request.getReviewText(), request.getReviewStyle());
        Review review = Review.builder()
                .reviewText(request.getReviewText())
                .reviewStyle(request.getReviewStyle())
                .generatedReply(response.getGeneratedReply())
                .build();

        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getReviewId(),
                savedReview.getReviewText(),
                savedReview.getReviewStyle(),
                savedReview.getGeneratedReply()
        );
    }
}
