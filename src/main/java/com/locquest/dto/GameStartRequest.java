package com.locquest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class GameStartRequest {
    private Long userId;
    private LocalDateTime startTime;
    private String gameMode;
    private LocalDate gameDate;
    private Long locCategory;
}

