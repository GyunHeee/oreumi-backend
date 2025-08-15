package com.oreumi.oreumi_backend.domain.review.entity;

import com.oreumi.oreumi_backend.common.entity.BaseEntity;
import com.oreumi.oreumi_backend.domain.history.entity.History;
import com.oreumi.oreumi_backend.domain.review.ReviewStyle;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(columnDefinition = "TEXT")
    private String reviewText;

    @Enumerated(EnumType.STRING)
    private ReviewStyle reviewStyle;

    @Column(columnDefinition = "TEXT")
    private String generatedReply;

    @OneToMany(mappedBy = "review")
    private List<History> histories;
}