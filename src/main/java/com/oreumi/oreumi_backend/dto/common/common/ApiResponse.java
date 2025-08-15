package com.oreumi.oreumi_backend.dto.common.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String error;

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
    }

    public ApiResponse(String errorMessage) {
        this.success = false;
        this.error = errorMessage;
    }
}
