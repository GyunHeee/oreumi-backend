package com.oreumi.oreumi_backend.controller;

import com.oreumi.oreumi_backend.controller.dto.HistoryResponse;
import com.oreumi.oreumi_backend.domain.review.ReviewStyle;
import com.oreumi.oreumi_backend.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;


    @GetMapping
    public HistoryResponse getHistoryData() {
        return historyService.getHistoryData();
    }

}
