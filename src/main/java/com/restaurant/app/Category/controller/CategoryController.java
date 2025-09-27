package com.restaurant.app.Category.controller;

import com.restaurant.app.Category.entity.Category;
import com.restaurant.app.Category.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController
{
    //Inyeccion de dependencia de service
    private final CategoryService categoryService;

    public CategoryController (CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    //Get all categories
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //Get category by id
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //Get category by name ignoring case
    @GetMapping("/name/{name}")
    public Category getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

    //Create category
    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }
    //Update category
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }

    //Delete category
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

}
