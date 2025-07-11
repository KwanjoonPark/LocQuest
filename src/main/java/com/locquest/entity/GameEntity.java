package com.locquest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private Long success;

    @Column
    private Long locCount;

    @Column
    private LocalDate gameDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity locCategory;

    @Column
    private Long gameChance;
}

