package com.locquest.service;

import com.locquest.dto.KakaoUserInfo;
import com.locquest.entity.UserEntity;
import com.locquest.repository.UserRepository;
import com.locquest.util.JwtTokenProvider;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String loginWithKakao(KakaoUserInfo userInfo) {
        // 이미 가입된 유저인지 확인
        UserEntity user = userRepository.findById(userInfo.getId())
                .orElseGet(() -> {
                    // 없으면 신규 등록
                    UserEntity newUser = new UserEntity();
                    newUser.setUserId(userInfo.getId());
                    newUser.setNickname(userInfo.getNickname());
                    newUser.setProfileImage(userInfo.getProfileImage());
                    newUser.setRegisteredDate(LocalDateTime.now());
                    return userRepository.save(newUser);
                });

        // JWT 토큰 발급
        return jwtTokenProvider.createToken(user.getUserId().toString());
    }
}
