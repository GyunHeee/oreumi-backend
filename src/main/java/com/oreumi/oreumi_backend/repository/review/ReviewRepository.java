package com.oreumi.oreumi_backend.repository.review;

import com.oreumi.oreumi_backend.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
