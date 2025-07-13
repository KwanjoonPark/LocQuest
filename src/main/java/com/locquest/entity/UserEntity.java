package com.locquest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private Long userId; // kakao ID

    @Column
    private String nickname;

    @Column
    private String profileImage;

    @Column
    private LocalDateTime registeredDate;

    @PrePersist
    public void onCreate() {
        this.registeredDate = LocalDateTime.now();
    }
}

