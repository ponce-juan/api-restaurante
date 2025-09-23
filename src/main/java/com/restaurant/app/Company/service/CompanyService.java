package com.restaurant.app.Company.service;


import com.restaurant.app.Company.entity.Company;
import lombok.NonNull;

public interface CompanyService {
    Company getCompanyById(@NonNull Long id);
    Company createCompany(@NonNull Company company);
    Company updateCompany(@NonNull Long id, @NonNull Company company);
    void deleteCompany(@NonNull Long id);

    int getCompanyTables(@NonNull Long id);
}
