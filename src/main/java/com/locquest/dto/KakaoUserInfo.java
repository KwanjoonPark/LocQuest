package com.locquest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserInfo {
    private Long id;
    private String nickname;
    private String profileImage;


    @Override
    public String toString() {
        return "KakaoUserInfo{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
