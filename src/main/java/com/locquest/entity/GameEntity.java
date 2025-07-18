package com.locquest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    private String gameMode; // EXPLORER, TIME ATTACK 모드

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime; // 게임 시작 시간

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime; // 게임 종료 시간

    @Column
    private Boolean success; // 게임 성공 여부

    @Column
    private Integer locCount; // 찾은 장소 개수

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate gameDate; // 게임 실행 일자

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity locCategory;

    @Column
    private Integer hintCount; // 사용한 게임 힌투 수
}

