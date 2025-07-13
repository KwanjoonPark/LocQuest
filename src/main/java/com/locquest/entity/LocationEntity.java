package com.locquest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "location_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locId;

    @Column
    private String locName;

    @Column
    private double locLat;

    @Column
    private double locLng;

    @Column
    private String locImage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column
    private Integer locFailed;

    @Column
    private Integer locSuccessed;
}
