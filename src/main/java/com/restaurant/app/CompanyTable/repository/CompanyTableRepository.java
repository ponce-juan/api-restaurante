package com.restaurant.app.CompanyTable.repository;

import com.restaurant.app.CompanyTable.entity.CompanyTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyTableRepository extends JpaRepository<CompanyTable, Long> {
    CompanyTable findByTableNumberAndCompanyId(int tableNumber, Long companyId);
    List<CompanyTable> findByCompanyId(Long companyId);
}
