package com.restaurant.app.Product.entity;

import com.restaurant.app.Product.dto.ProductDTO;

public class ProductMapper {
    public static ProductDTO toDTO(Product p){
        if(p == null) return null;

        return  ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .stock(p.getStock())
                .category(p.getCategory() != null ? p.getCategory().getName() : null)
                .subCategory(p.getSubCategory() != null ? p.getSubCategory().getName() : null)
                .build();
    }

    public static Product toEntity (ProductDTO dto){
        if(dto == null) return null;

        Product prod = new Product();
        prod.setName(dto.name());
        prod.setDescription(dto.description());
        prod.setPrice(dto.price());
        prod.setStock(dto.stock());
        // No category/subcategory/company
        return prod;
    }

}
