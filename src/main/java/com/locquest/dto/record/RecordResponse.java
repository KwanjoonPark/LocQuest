package com.locquest.dto.record;

public interface RecordResponse {
    Integer getGameId();
    String getGameMode();
    Boolean getSuccess();
    String getGameDate();     // or LocalDate
    Integer getCategoryId();
    Integer getHintCount();
}
