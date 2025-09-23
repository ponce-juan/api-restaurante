package com.restaurant.app.Category.service;

import com.restaurant.app.Category.entity.Category;
import lombok.NonNull;

import java.util.List;

public interface CategoryService
{
    List<Category> getAllCategories();
    Category getCategoryById(@NonNull Long id);
    Category createCategory(@NonNull Category category);
    Category updateCategory(@NonNull Long id, @NonNull Category category);
    void deleteCategory(@NonNull Long id);

    Category getCategoryByName (@NonNull String name);
}
