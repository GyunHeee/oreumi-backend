package com.oreumi.oreumi_backend.dto.gpt;

import com.oreumi.oreumi_backend.domain.review.ReviewStyle;
import lombok.Data;

@Data
public class GptReviewResponse {
    private String reviewText;
    private ReviewStyle reviewStyle;
    private String generatedReply;

    public GptReviewResponse(String reviewText, ReviewStyle reviewStyle, String generatedReply) {
        this.reviewText = reviewText;
        this.reviewStyle = reviewStyle;
        this.generatedReply = generatedReply;
    }
}
