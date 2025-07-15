package com.locquest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EndGameResponse {
    private final Double elapsedSeconds;
}
