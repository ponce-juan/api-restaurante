package com.restaurant.app.Product.service;

import com.restaurant.app.Product.entity.Product;

import java.util.List;

public interface ProductService
{
    // Operaciones CRUD para la entidad Product
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

    // Otras operaciones extras
    List<Product> getProductsByCategoryId(Long categoryId);
    List<Product> getProductsBySubCategoryId(Long subCategoryId);
    List<Product> getProductsByName(String name);

}
