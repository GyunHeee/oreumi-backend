package com.oreumi.oreumi_backend.service.review;

import com.oreumi.oreumi_backend.domain.history.HistoryType;
import com.oreumi.oreumi_backend.domain.history.entity.History;
import com.oreumi.oreumi_backend.domain.review.entity.Review;
import com.oreumi.oreumi_backend.dto.gpt.GptReviewRequest;
import com.oreumi.oreumi_backend.dto.gpt.GptReviewResponse;
import com.oreumi.oreumi_backend.dto.review.ReviewResponse;
import com.oreumi.oreumi_backend.exception.ReviewNotFoundException;
import com.oreumi.oreumi_backend.repository.history.HistoryRepository;
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
    private final HistoryRepository historyRepository;

    @Transactional
    public ReviewResponse getGptReview(GptReviewRequest request) {
        GptReviewResponse response = gptReviewService.generateReview(request.getReviewText(), request.getReviewStyle());
        Review review = Review.builder()
                .reviewText(request.getReviewText())
                .reviewStyle(request.getReviewStyle())
                .generatedReply(response.getGeneratedReply())
                .build();

        Review savedReview = reviewRepository.save(review);
        
        // 리뷰 생성 시 히스토리 자동 생성
        History history = History.builder()
                .historyType(HistoryType.REVIEW)
                .review(savedReview)
                .build();
        historyRepository.save(history);
        
        return new ReviewResponse(
                savedReview.getReviewId(),
                savedReview.getReviewText(),
                savedReview.getReviewStyle(),
                savedReview.getGeneratedReply()
        );
    }

    public ReviewResponse findById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));

        return new ReviewResponse(
                review.getReviewId(),
                review.getReviewText(),
                review.getReviewStyle(),
                review.getGeneratedReply()
        );
    }
}
