package com.locquest.dto.ranking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExplorerRankEntry {
    private String userId;
    private String userName;
    private int locCount;
    private int hintCount;
}
