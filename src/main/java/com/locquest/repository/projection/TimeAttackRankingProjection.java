package com.locquest.repository.projection;

public interface TimeAttackRankingProjection {
    String getUserId();
    String getUserName();
    Integer getTotalTime(); // 초 단위
}