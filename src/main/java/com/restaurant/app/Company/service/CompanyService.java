package com.restaurant.app.Company.service;


import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.CompanyTable.entity.CompanyTable;
import lombok.NonNull;

import java.util.List;

public interface CompanyService {
    Company getCompanyById(@NonNull Long id);
    Company createCompany(@NonNull Company company);
    Company updateCompany(@NonNull Long id, @NonNull Company company);
    void deleteCompany(@NonNull Long id);

    List<CompanyTable> getCompanyTables(@NonNull Long id);
}
