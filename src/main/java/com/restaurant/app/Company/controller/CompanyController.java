package com.restaurant.app.Company.controller;

import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.Company.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companies")
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") Long id){
        return companyService.getCompanyById(id);
    }

    @GetMapping("/{id}/tables")
    public int getCompanyTables(@PathVariable("id") Long id){
        return companyService.getCompanyTables(id);
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable("id") Long id, @RequestBody Company company){
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") Long id){
        companyService.deleteCompany(id);
    }




}
