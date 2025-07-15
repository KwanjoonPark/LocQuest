package com.locquest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SendSuccessRequest {
    private Long userId;
    private Long locId;
    private Long gameId;
    private LocalDateTime completeDate;
}