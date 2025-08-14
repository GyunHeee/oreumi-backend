package com.oreumi.oreumi_backend.domain.history.entity;

import com.oreumi.oreumi_backend.common.entity.BaseEntity;
import com.oreumi.oreumi_backend.domain.history.HistoryType;
import com.oreumi.oreumi_backend.domain.product.entity.Product;
import com.oreumi.oreumi_backend.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "History")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class History extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
    
    @Enumerated(EnumType.STRING)
    private HistoryType historyType;
}