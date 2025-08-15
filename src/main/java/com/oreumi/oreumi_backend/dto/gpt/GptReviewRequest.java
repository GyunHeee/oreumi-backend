package com.oreumi.oreumi_backend.dto.gpt;

import com.oreumi.oreumi_backend.domain.review.ReviewStyle;
import lombok.Data;

@Data
public class GptReviewRequest {
    private String reviewText;
    private ReviewStyle reviewStyle;
}
