package com.oreumi.oreumi_backend.dto.gpt;

import lombok.Data;

@Data
public class GptProductResponse {
    private String title;
    private String description;

    public GptProductResponse(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
