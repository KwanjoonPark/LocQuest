package com.locquest.dto.record;

public interface GameDetailResponse {
    Long getLocId();
    String getLocName();
    Double getLocLat();
    Double getLocLng();
    String getLocImage();
    Integer getLocFailed();
    Integer getLocSuccessed();
}