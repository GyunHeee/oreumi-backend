package com.oreumi.oreumi_backend.service.product;

import com.oreumi.oreumi_backend.domain.product.entity.Product;
import com.oreumi.oreumi_backend.dto.gpt.GptProductRequest;
import com.oreumi.oreumi_backend.dto.gpt.GptProductResponse;
import com.oreumi.oreumi_backend.dto.product.ProductResponse;
import com.oreumi.oreumi_backend.repository.product.ProductRepository;
import com.oreumi.oreumi_backend.service.gpt.GptProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final GptProductService gptProductService;
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse getGptProduct(String inputText) {
        GptProductResponse response = gptProductService.generateProduct(inputText);

        Product product = Product.builder()
                .generatedDescription(response.getDescription())
                .generatedTitle(response.getTitle())
                .inputText(inputText)
                .build();

        Product savedProduct = productRepository.save(product);
        return new ProductResponse(
                product.getProductId(),
                product.getInputText(),
                product.getGeneratedTitle(),
                product.getGeneratedDescription());
    }
}
