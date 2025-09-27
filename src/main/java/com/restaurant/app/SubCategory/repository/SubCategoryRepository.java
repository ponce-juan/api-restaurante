package com.restaurant.app.SubCategory.repository;

import com.restaurant.app.SubCategory.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>
{
    Optional<SubCategory> findByNameIgnoreCase(String name);
}
