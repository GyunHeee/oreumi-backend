package com.oreumi.oreumi_backend.dto.review;

import com.oreumi.oreumi_backend.domain.review.ReviewStyle;
import lombok.Data;

@Data
public class ReviewResponse {
    private Long reviewId;
    private String reviewText;
    private ReviewStyle reviewStyle;
    private String generatedReply;

    public ReviewResponse(Long reviewId, String reviewText, ReviewStyle reviewStyle, String generatedReply) {
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.reviewStyle = reviewStyle;
        this.generatedReply = generatedReply;
    }
}
