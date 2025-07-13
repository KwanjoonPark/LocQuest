package com.locquest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locquest.dto.KakaoTokenResponse;
import com.locquest.dto.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KakaoOAuthService {

    @Value("${kakao.client-id}")
    private String clientId;    // application.yml에 설정
    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public KakaoUserInfo loginWithAuthCode(String authCode) {
        // 1) 토큰 교환
        KakaoTokenResponse tokenResp = issueToken(authCode);

        // 2) 발급된 액세스 토큰으로 사용자 정보 조회
        return fetchUserInfo(tokenResp.getAccessToken());
    }

    private KakaoTokenResponse issueToken(String authCode) {
        String uri = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", authCode);

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoTokenResponse> resp = restTemplate.postForEntity(
                uri, entity, KakaoTokenResponse.class
        );
        if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
            throw new RuntimeException("카카오 토큰 교환 실패: " + resp.getStatusCode());
        }
        return resp.getBody();
    }

    private KakaoUserInfo fetchUserInfo(String accessToken) {
        String uri = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<String> resp = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class
        );

        try {
            JsonNode root = objectMapper.readTree(resp.getBody());
            Long id = root.get("id").asLong();
            JsonNode prop = root.get("properties");
            String nickname = prop.get("nickname").asText();
            String profileImage = prop.get("profile_image").asText();
            return new KakaoUserInfo(id, nickname, profileImage);
        } catch (Exception e) {
            throw new RuntimeException("카카오 사용자 정보 파싱 실패", e);
        }
    }
}

