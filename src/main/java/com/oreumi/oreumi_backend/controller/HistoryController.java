package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.controller.dto.HistoryResponse;
import com.oreumi.oreumi_backend.controller.dto.ProductDetailResponse;
import com.oreumi.oreumi_backend.controller.dto.ReviewDetailResponse;
import com.oreumi.oreumi_backend.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    /**
     * 히스토리 데이터 조회
     * @return 히스토리 응답 데이터
     */
    @GetMapping
    public HistoryResponse getHistoryData() {
        return historyService.getHistoryData();
    }

}
