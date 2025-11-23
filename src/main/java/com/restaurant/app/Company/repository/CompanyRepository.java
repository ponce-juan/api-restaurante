package com.restaurant.app.Company.repository;

import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.CompanyTable.entity.CompanyTable;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByName(String name);

}
