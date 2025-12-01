package com.restaurant.app.Product.service;

import com.restaurant.app.Product.dto.ProductDTO;
import com.restaurant.app.Product.entity.Product;

import java.util.List;

public interface ProductService
{
    // Operaciones CRUD para la entidad Product
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(Product product);
    ProductDTO updateProduct(Long id, ProductDTO product);
    void deleteProduct(Long id);

    // Otras operaciones extras
    List<ProductDTO> getProductsByCategoryId(Long categoryId);
    List<ProductDTO> getProductsBySubCategoryId(Long subCategoryId);
    List<ProductDTO> getProductsByName(String name);

}
