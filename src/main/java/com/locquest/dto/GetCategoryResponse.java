package com.locquest.dto;

import com.locquest.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCategoryResponse {
    private List<CategoryEntity> categoryList;
}
