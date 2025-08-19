package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.controller.dto.HistoryResponse;
import com.oreumi.oreumi_backend.controller.dto.HistoryCreateRequest;
import com.oreumi.oreumi_backend.domain.history.entity.History;
import com.oreumi.oreumi_backend.domain.review.ReviewStyle;
import com.oreumi.oreumi_backend.service.history.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
@Tag(name = "History", description = "히스토리 관련 API")
public class HistoryController {

    private final HistoryService historyService;


    @Operation(summary = "히스토리 데이터 조회", description = "상품과 리뷰 히스토리를 조회합니다.")
    @GetMapping
    public HistoryResponse getHistoryData() {
        return historyService.getHistoryData();
    }

    @Operation(summary = "히스토리 생성", description = "새로운 히스토리를 생성합니다.")
    @PostMapping
    public History createHistory(@RequestBody HistoryCreateRequest request) {
        return historyService.createHistory(request);
    }

}
