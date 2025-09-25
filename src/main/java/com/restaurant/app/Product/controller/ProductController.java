package com.restaurant.app.Product.controller;

import com.restaurant.app.Product.entity.Product;
import com.restaurant.app.Product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController
{
    private final ProductService productService;

    public ProductController (ProductService productService)
    {
        this.productService = productService;
    }

    // Basic operations CRUD for the entity Product
    //Get all products
    @GetMapping
    public List<Product> getAllProducts ()
    {
        return productService.getAllProducts();
    }

    //Get product by id
    @GetMapping("/{id}")
    public Product getProductById (@PathVariable Long id)
    {
        return productService.getProductById(id);
    }
    //Create product
    @PostMapping
    public Product createProduct (@RequestBody Product product)
    {

        return productService.createProduct(product);
    }

    //Update product
    @PutMapping("/{id}")
    public Product updateProduct (@PathVariable Long id, @RequestBody Product product)
    {
        return productService.updateProduct(id, product);
    }

    //Delete product
    @DeleteMapping("/{id}")
    void deleteProduct (@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }


    // Extra operations
    //Get products by category id
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategoryId (@PathVariable Long categoryId)
    {
        return productService.getProductsByCategoryId(categoryId);
    }
    //Get products by subcategory id
    @GetMapping("/subcategory/{subCategoryId}")
    public List<Product> getProductsBySubCategoryId (@PathVariable Long subCategoryId){
        return productService.getProductsBySubCategoryId(subCategoryId);
    }

    //Get products by name ignore case
    @GetMapping("/name/{name}")
    public List<Product> getProductsByName (@PathVariable String name)
    {
        return productService.getProductsByName(name);
    }




}
