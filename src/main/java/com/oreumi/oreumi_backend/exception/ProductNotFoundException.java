package com.oreumi.oreumi_backend.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("존재하지 않는 상품입니다. ID: " + id);
    }
}
