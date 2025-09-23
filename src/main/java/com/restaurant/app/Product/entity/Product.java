package com.restaurant.app.Product.entity;

import com.restaurant.app.Category.entity.Category;
import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.SubCategory.entity.SubCategory;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

}
