package com.restaurant.app.Company.service;

import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.Company.repository.CompanyRepository;
import com.restaurant.app.Utils.StringUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImp implements CompanyService {
    private CompanyRepository companyRepository;

    @Override
    public Company getCompanyById(@NonNull Long id) {
        return companyRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + id));
    }

    @Override
    public Company createCompany(@NonNull Company company) {
        if(companyRepository.existsByName(company.getName()))
            throw new EntityExistsException("Company already exists with name: " + company.getName());

        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(@NonNull Long id, @NonNull Company companyUpdate) {

        Company company = this.getCompanyById(id);

        company.setName(StringUtils.isNullOrBlank(companyUpdate.getName()) ? company.getName() :
                companyUpdate.getName());
        company.setTablesCount(companyUpdate.getTablesCount() >= 0 ? company.getTablesCount() :
                companyUpdate.getTablesCount());


        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(@NonNull Long id) {
        Company company = this.getCompanyById(id); // throws an error if the company doesn't exist

        companyRepository.deleteById(id);
    }

    @Override
    public int getCompanyTables(@NonNull Long id) {
        return this.getCompanyById(id).getTablesCount();
    }
}
