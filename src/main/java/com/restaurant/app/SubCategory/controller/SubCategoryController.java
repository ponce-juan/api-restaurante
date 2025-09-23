package com.restaurant.app.SubCategory.controller;

import com.restaurant.app.SubCategory.entity.SubCategory;
import com.restaurant.app.SubCategory.service.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subcategories")
public class SubCategoryController
{
    private final SubCategoryService subCategoryService;
    public SubCategoryController (SubCategoryService subCategoryService)
    {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public List<SubCategory> getAllSubCategories ()
    {
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/{id}")
    public SubCategory getSubCategoryById (@PathVariable Long id)
    {
        return subCategoryService.getSubCategoryById(id);
    }

    @PostMapping
    public SubCategory createSubCategory (@RequestBody SubCategory subCategory){
        return subCategoryService.createSubCategory(subCategory);
    }

    @PutMapping("/{id}")
    public SubCategory updateSubCategory (@PathVariable Long id, @RequestBody SubCategory subCategory){
        return subCategoryService.updateSubCategory(id, subCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteSubCategory (@PathVariable Long id){
        subCategoryService.deleteSubCategory(id);
    }

}
