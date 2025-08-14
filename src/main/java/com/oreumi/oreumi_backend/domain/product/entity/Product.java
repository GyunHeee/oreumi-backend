package com.oreumi.oreumi_backend.domain.product.entity;

import com.oreumi.oreumi_backend.common.entity.BaseEntity;
import com.oreumi.oreumi_backend.domain.history.entity.History;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(columnDefinition = "TEXT")
    private String inputText;

    private String generatedTitle;

    @Column(columnDefinition = "TEXT")
    private String generatedDescription;

    @OneToMany(mappedBy = "product")
    private List<History> histories;
}