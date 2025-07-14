package com.locquest.dto;

import com.locquest.entity.LocationEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameStartResponse {
    private Long gameId;
    private Long locCategory;
    private List<LocationEntity> locationList;
}

