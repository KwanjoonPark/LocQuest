package com.locquest.service;

import com.locquest.dto.ranking.ExplorerRankEntry;
import com.locquest.dto.ranking.TimeAttackRankEntry;
import com.locquest.entity.GameEntity;
import com.locquest.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final GameRepository gameRepository;

    public List<ExplorerRankEntry> getExplorerRanking(Long categoryId) {
        return gameRepository.getExplorerRankingByCategory(categoryId).stream()
                .map(p -> new ExplorerRankEntry(
                        p.getUserId(),
                        p.getUserName(),
                        p.getTotalLocCount(),
                        p.getTotalHintUsed()
                ))
                .toList();
    }

    public List<TimeAttackRankEntry> getTimeAttackRanking(Long categoryId) {
        return gameRepository.getTimeAttackRankingByCategory(categoryId).stream()
                .map(p -> new TimeAttackRankEntry(
                        p.getUserId(),
                        p.getUserName(),
                        p.getTotalTime()
                ))
                .toList();
    }
}

