package com.restaurant.app.Company.repository;

import com.restaurant.app.Company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByName(String name);
}
