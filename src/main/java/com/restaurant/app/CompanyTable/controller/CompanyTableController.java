package com.restaurant.app.CompanyTable.controller;

import com.restaurant.app.CompanyTable.entity.CompanyTable;
import com.restaurant.app.CompanyTable.service.CompanyTableService;
import com.restaurant.app.Utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
@AllArgsConstructor
public class CompanyTableController {
    private final CompanyTableService companyTableService;

    @GetMapping
    public List<CompanyTable> getCompanyTablesByCompanyId(){
        Long companyId = SecurityUtils.getCompanyId();
        if(companyId == null)
            throw new IllegalStateException("Company ID not found in security context");


        return companyTableService.getCompanyTablesByCompanyId(companyId);
    }

    @GetMapping("/{tableNumber}")
    public CompanyTable getCompanyTableByNumberAndCompanyId(@PathVariable("tableNumber") int tableNumber){
        Long companyId = SecurityUtils.getCompanyId();
        if(companyId == null)
            throw new IllegalStateException("Company ID not found in security context");

        return companyTableService.getCompanyTableByNumberAndCompanyId(tableNumber, companyId);
    }

    @DeleteMapping("/{tableNumber}")
    public void deleteCompanyTable(@PathVariable("tableNumber") int tableNumber){

        Long companyId = SecurityUtils.getCompanyId();
        if(companyId == null)
            throw new IllegalStateException("Company ID not found in security context");

        companyTableService.deleteCompanyTable(companyId, tableNumber);
    }

    @PostMapping
    public CompanyTable createCompanyTable(@RequestBody CompanyTable table){
        Long companyId = SecurityUtils.getCompanyId();
        if(companyId == null)
            throw new IllegalStateException("Company ID not found in security context");

        return companyTableService.createCompanyTable(companyId, table);
    }

    @PutMapping("/{tableId}")
    public CompanyTable updateCompanyTable(@PathVariable("tableId") Long tableId, @RequestBody CompanyTable table){
        Long companyId = SecurityUtils.getCompanyId();
        if(companyId == null)
            throw new IllegalStateException("Company ID not found in security context");

        table.setId(tableId);
        return companyTableService.updateCompanyTable(companyId, table);
    }


}
