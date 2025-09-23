package com.restaurant.app.Product.entity;

import com.restaurant.app.Product.dto.ProductDTO;

public class ProductMapper {
    public static ProductDTO toDTO(Product p){
        if(p == null) return null;

        return  ProductDTO.builder()
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .stock(p.getStock())
                .categoryName(p.getCategory() != null ? p.getCategory().getName() : null)
                .subcategoryName(p.getSubCategory() != null ? p.getSubCategory().getName() : null)
                .build();
    }

    public static Product toEntity (ProductDTO dto){
        if(dto == null) return null;

        Product prod = new Product();
        prod.setName(dto.getName());
        prod.setDescription(dto.getDescription());
        prod.setPrice(dto.getPrice());
        prod.setStock(dto.getStock());
        // No category/subcategory/company
        return prod;
    }

}
