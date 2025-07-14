package com.locquest.repository;

import com.locquest.entity.GameEntity;
import com.locquest.repository.projection.ExplorerRankingProjection;
import com.locquest.repository.projection.TimeAttackRankingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    @Query(value = """
        SELECT 
            g.user_id AS userId,
            u.nickname AS userName,
            SUM(g.loc_count) AS totalLocCount,
            SUM(COALESCE(g.hint_count, 0)) AS totalHintUsed
        FROM game_tbl g
        JOIN user_tbl u ON g.user_id = u.user_id
        WHERE g.game_mode = 'Explorer'
          AND (:categoryId = 0 OR g.category_id = :categoryId)
        GROUP BY g.user_id
        ORDER BY totalLocCount DESC, totalHintUsed ASC
        LIMIT 10
        """, nativeQuery = true)
    List<ExplorerRankingProjection> getExplorerRankingByCategory(@Param("categoryId") Long categoryId);

    @Query(value = """
    SELECT 
        g.user_id AS userId,
        u.nickname AS userName,
        SUM(TIMESTAMPDIFF(SECOND, g.start_time, g.end_time)) AS totalTime
    FROM game_tbl g
    JOIN user_tbl u ON g.user_id = u.user_id
    WHERE g.game_mode = 'TimeAttack'
      AND (:categoryId = 0 OR g.category_id = :categoryId)
      AND g.end_time IS NOT NULL
    GROUP BY g.user_id
    ORDER BY totalTime ASC
    LIMIT 10
    """, nativeQuery = true)
    List<TimeAttackRankingProjection> getTimeAttackRankingByCategory(@Param("categoryId") Long categoryId);
}

