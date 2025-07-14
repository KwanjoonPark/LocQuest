package com.locquest.dto.ranking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class getTimeAttackRankingResponse {
    private List<TimeAttackRankEntry> timeAttackRanking;
}