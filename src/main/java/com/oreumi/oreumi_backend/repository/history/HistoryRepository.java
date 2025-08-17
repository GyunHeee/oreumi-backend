package com.oreumi.oreumi_backend.repository.history;

import com.oreumi.oreumi_backend.domain.history.entity.History;
import com.oreumi.oreumi_backend.domain.history.HistoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    
    List<History> findByHistoryTypeOrderByCreatedAtDesc(HistoryType historyType);
} 