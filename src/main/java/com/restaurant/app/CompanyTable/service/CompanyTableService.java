package com.restaurant.app.CompanyTable.service;

import com.restaurant.app.CompanyTable.dto.CompanyTableDTO;
import com.restaurant.app.CompanyTable.entity.CompanyTable;
import lombok.NonNull;

import java.util.List;

public interface CompanyTableService {
    CompanyTableDTO getCompanyTableByNumberAndCompanyId(int tableNumber, @NonNull Long companyId);
    CompanyTableDTO createCompanyTable(@NonNull Long companyId, @NonNull CompanyTable table);
    CompanyTableDTO updateCompanyTable(@NonNull Long companyId, @NonNull CompanyTable table);
    void deleteCompanyTable(@NonNull Long companyId, int tableNumber);

    List<CompanyTableDTO> getCompanyTablesByCompanyId(@NonNull Long companyId);
}
