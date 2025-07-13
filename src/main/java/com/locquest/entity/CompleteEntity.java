package com.locquest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "complete_tbl")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long completeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @Column
    private LocalDateTime completeDate;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
}

