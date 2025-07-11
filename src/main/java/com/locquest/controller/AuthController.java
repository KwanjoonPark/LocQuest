package com.locquest.controller;

import com.locquest.dto.KakaoUserInfo;
import com.locquest.service.AuthService;
import com.locquest.service.KakaoOAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final KakaoOAuthService kakaoOAuthService;
    private final AuthService authService;

    @PostMapping("/kakaoLogin")
    public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> request) {
        String kakaoAccessToken = request.get("accessToken");

        KakaoUserInfo userInfo = kakaoOAuthService.getUserInfo(kakaoAccessToken);
        String jwt = authService.loginWithKakao(userInfo);

        return ResponseEntity.ok(Map.of("jwt", jwt));
    }
}
