package com.locquest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.locquest.entity.LocationEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EndGameRequest {

    private Long gameId;
    private Boolean success;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private Integer locCount;
    private Integer hintCount;

    private List<LocationEntity> failedLocations;
}

