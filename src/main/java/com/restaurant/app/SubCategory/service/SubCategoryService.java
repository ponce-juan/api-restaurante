package com.restaurant.app.SubCategory.service;

import com.restaurant.app.SubCategory.entity.SubCategory;
import lombok.NonNull;

import java.util.List;


public interface SubCategoryService
{
    List<SubCategory> getAllSubCategories();
    SubCategory getSubCategoryById(@NonNull Long id);
    SubCategory createSubCategory(@NonNull SubCategory subCategory);
    SubCategory updateSubCategory(@NonNull Long id, @NonNull SubCategory subCategory);
    void deleteSubCategory(@NonNull Long id);
}
