package com.oreumi.oreumi_backend.repository.history;

import com.oreumi.oreumi_backend.domain.history.entity.History;
import com.oreumi.oreumi_backend.domain.history.HistoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    
    // List<History> findByHistoryTypeOrderByCreatedAtDesc(HistoryType historyType);

    @Query("SELECT h FROM History h LEFT JOIN FETCH h.product " +
            "WHERE h.historyType = :type " +
            "ORDER BY h.createdAt DESC")
    List<History> findProductHistoriesByType(@Param("type") HistoryType type);

    @Query("SELECT h FROM History h LEFT JOIN FETCH h.review " +
            "WHERE h.historyType = :type " +
            "ORDER BY h.createdAt DESC")
    List<History> findReviewHistoriesByType(@Param("type") HistoryType type);

} 