package com.locquest.service;

import com.locquest.dto.UpLoadLocationRequest;
import com.locquest.entity.CategoryEntity;
import com.locquest.entity.LocationEntity;
import com.locquest.repository.CategoryRepository;
import com.locquest.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    public LocationEntity createLocation(UpLoadLocationRequest request, String savedPath) {
        CategoryEntity category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        LocationEntity location = LocationEntity.builder()
                .locName(request.getLocName())
                .locLat(request.getLatitude())
                .locLng(request.getLongitude())
                .category(category)
                .locImage(savedPath)
                .build();

        return locationRepository.save(location);
    }
}
