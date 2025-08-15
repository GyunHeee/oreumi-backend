package com.oreumi.oreumi_backend.domain.product.entity;

import com.oreumi.oreumi_backend.common.entity.BaseEntity;
import com.oreumi.oreumi_backend.domain.history.entity.History;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Product(String inputText, String generatedTitle, String generatedDescription) {
        this.inputText = inputText;
        this.generatedTitle = generatedTitle;
        this.generatedDescription = generatedDescription;
    }
}