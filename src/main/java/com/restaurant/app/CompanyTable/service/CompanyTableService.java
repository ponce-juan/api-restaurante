package com.restaurant.app.CompanyTable.service;

import com.restaurant.app.CompanyTable.entity.CompanyTable;
import lombok.NonNull;

import java.util.List;

public interface CompanyTableService {
    CompanyTable getCompanyTableByNumberAndCompanyId(int tableNumber, @NonNull Long companyId);
    CompanyTable createCompanyTable(@NonNull Long companyId, @NonNull CompanyTable table);
    CompanyTable updateCompanyTable(@NonNull Long companyId, @NonNull CompanyTable table);
    void deleteCompanyTable(@NonNull Long companyId, int tableNumber);

    List<CompanyTable> getCompanyTablesByCompanyId(@NonNull Long companyId);
}
