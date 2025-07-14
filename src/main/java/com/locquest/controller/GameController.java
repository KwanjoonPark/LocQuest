package com.locquest.controller;

import com.locquest.dto.EndGameRequest;
import com.locquest.dto.GameStartRequest;
import com.locquest.dto.GameStartResponse;
import com.locquest.dto.SendSuccessRequest;
import com.locquest.entity.GameEntity;
import com.locquest.entity.LocationEntity;
import com.locquest.service.GameService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @PostMapping("/startGame")
    public ResponseEntity<GameStartResponse> startGame(@RequestBody GameStartRequest request, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        System.out.println("userId from JWT = " + userId);

        // 게임 생성
        GameEntity savedGame = gameService.createGame(userId, request);

        // 랜덤으로 위치 사진 5개 뽑기
        List<LocationEntity> locations = gameService.getRandomLocationsByCategory(savedGame.getLocCategory().getCategoryId());

        GameStartResponse response = new GameStartResponse();
        response.setGameId(savedGame.getGameId());
        response.setLocCategory(savedGame.getLocCategory().getCategoryId());
        response.setLocationList(locations);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendSuccess")
    public ResponseEntity<Void> sendSuccess(@RequestBody SendSuccessRequest request, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        gameService.recordComplete(userId, request);
        gameService.countSuccess(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/endGame")
    public ResponseEntity<Void> endGame(@RequestBody EndGameRequest request, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        gameService.finishGame(request);
        gameService.failedLocations(request.getFailedLocations());
        return ResponseEntity.ok().build();
    }
}
