package com.oreumi.oreumi_backend.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long id) {
        super("존재하지 않는 리뷰입니다. ID: " + id);
    }
}
