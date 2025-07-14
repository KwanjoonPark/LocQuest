package com.locquest.repository.projection;

public interface ExplorerRankingProjection {
    String getUserId();
    String getUserName();
    Integer getTotalLocCount();
    Integer getTotalHintUsed();
}