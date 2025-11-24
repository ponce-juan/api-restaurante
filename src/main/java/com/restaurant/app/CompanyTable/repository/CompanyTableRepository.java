package com.restaurant.app.CompanyTable.repository;

import com.restaurant.app.CompanyTable.entity.CompanyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyTableRepository extends JpaRepository<CompanyTable, Long> {
    CompanyTable findByNumberAndCompanyId(int tableNumber, Long companyId);
    List<CompanyTable> findByCompanyId(Long companyId);
}
