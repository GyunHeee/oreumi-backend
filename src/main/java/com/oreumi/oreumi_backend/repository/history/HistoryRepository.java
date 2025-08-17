package com.oreumi.oreumi_backend.repository.history;

import com.oreumi.oreumi_backend.domain.history.entity.History;
import com.oreumi.oreumi_backend.domain.history.HistoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    
    // 모든 히스토리 조회 (최신순)
    List<History> findAllByOrderByCreatedAtDesc();
    
    // 타입별 히스토리 조회 (최신순)
    List<History> findByHistoryTypeOrderByCreatedAtDesc(HistoryType historyType);
} 