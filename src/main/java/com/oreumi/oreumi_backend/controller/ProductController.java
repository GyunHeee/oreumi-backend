package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.domain.product.entity.Product;
import com.oreumi.oreumi_backend.dto.common.common.ApiResponse;
import com.oreumi.oreumi_backend.dto.gpt.GptProductRequest;
import com.oreumi.oreumi_backend.dto.gpt.GptProductResponse;
import com.oreumi.oreumi_backend.dto.product.ProductResponse;
import com.oreumi.oreumi_backend.service.gpt.GptProductService;
import com.oreumi.oreumi_backend.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<ProductResponse>> getGptProduct(@RequestBody GptProductRequest request) {
        try {
            ProductResponse productResponse = productService.getGptProduct(request.getProductInfo());
            return ResponseEntity.ok(new ApiResponse<>(productResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage()));
        }
    }
}
