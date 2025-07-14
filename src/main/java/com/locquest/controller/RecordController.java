package com.locquest.controller;

import com.locquest.dto.record.GameDetailResponse;
import com.locquest.dto.record.RecordResponse;
import com.locquest.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordController {

    private final GameRepository gameRepository;

    @GetMapping(value = "/getGames", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<RecordResponse>> getGames(@RequestParam int userId) {
        System.out.println("record response 실행");
        List<RecordResponse> result = gameRepository.findRecordResponsesByUserId(userId);
        System.out.println("record response 결과: " + result);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/gameDetail", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<GameDetailResponse>> getGameDetail(@RequestParam Long gameId) {
        System.out.println("details response 실행");
        List<GameDetailResponse> details = gameRepository.findDetailsByGameId(gameId);
        System.out.println("details response 결과: " + details);
        return ResponseEntity.ok(details);
    }
}

