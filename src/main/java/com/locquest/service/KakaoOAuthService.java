package com.locquest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locquest.dto.KakaoUserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoOAuthService {

    private final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    public KakaoUserInfo getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USER_INFO_URI, HttpMethod.GET, entity, String.class
        );

        // JSON 파싱
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            Long id = jsonNode.get("id").asLong();
            String email = jsonNode.get("kakao_account").get("email").asText();
            String nickname = jsonNode.get("properties").get("nickname").asText();
            String profileImage = jsonNode.get("properties").get("profile_image").asText();

            return new KakaoUserInfo(id, email, nickname, profileImage);

        } catch (Exception e) {
            throw new RuntimeException("카카오 사용자 정보 파싱 실패", e);
        }
    }
}

