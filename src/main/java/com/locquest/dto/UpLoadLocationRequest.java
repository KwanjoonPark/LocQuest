package com.locquest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpLoadLocationRequest {
    private String locName;
    private Long categoryId;
    private Double latitude;
    private Double longitude;
    private MultipartFile image;
}
