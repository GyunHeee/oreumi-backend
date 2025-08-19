package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.domain.product.entity.Product;
import com.oreumi.oreumi_backend.dto.common.common.ApiResponse;
import com.oreumi.oreumi_backend.dto.gpt.GptProductRequest;
import com.oreumi.oreumi_backend.dto.gpt.GptProductResponse;
import com.oreumi.oreumi_backend.dto.product.ProductResponse;
import com.oreumi.oreumi_backend.service.gpt.GptProductService;
import com.oreumi.oreumi_backend.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 생성", description = "GPT API로 상품에 대한 응답을 생성합니다.")
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

    @Operation(summary = "상품 데이터 단 건 조회", description = "상품 데이터를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ApiResponse<>(productService.findById(id)));
    }
}
