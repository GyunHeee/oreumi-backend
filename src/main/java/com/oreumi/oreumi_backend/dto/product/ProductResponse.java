package com.oreumi.oreumi_backend.dto.product;

import lombok.Data;

@Data
public class ProductResponse {
    private Long productId;
    private String inputText;
    private String generatedTitle;
    private String generatedDescription;

    public ProductResponse(Long productId, String inputText, String generatedTitle, String generatedDescription) {
        this.productId = productId;
        this.inputText = inputText;
        this.generatedTitle = generatedTitle;
        this.generatedDescription = generatedDescription;
    }
}
