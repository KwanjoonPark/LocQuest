package com.locquest.repository;

import com.locquest.entity.LocationEntity;
import com.locquest.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    List<LocationEntity> findByCategory(CategoryEntity category);
}
