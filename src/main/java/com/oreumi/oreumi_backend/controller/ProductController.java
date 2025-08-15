package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.dto.gpt.GptProductRequest;
import com.oreumi.oreumi_backend.dto.gpt.GptProductResponse;
import com.oreumi.oreumi_backend.service.gpt.GptProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final GptProductService gptProductService;

    @PostMapping("/generate")
    public ResponseEntity<GptProductResponse> getGptProduct(@RequestBody GptProductRequest request) {
        GptProductResponse response = gptProductService.generateProduct(request.getProductInfo());
        return ResponseEntity.ok(response);
    }
}
