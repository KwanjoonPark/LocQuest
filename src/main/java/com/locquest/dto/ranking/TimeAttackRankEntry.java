package com.locquest.dto.ranking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeAttackRankEntry {
    private String userId;
    private String userName;
    private int totalTime;
}