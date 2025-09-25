package com.restaurant.app.Product.repository;

import com.restaurant.app.Product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findBySubCategoryId(Long subCategoryId);
    List<Product> findByNameContainingIgnoreCase(String name);
    Product findByNameIgnoreCaseAndCompanyId(String name, Long id);
    List<Product> findByCompanyId(Long id);
}
