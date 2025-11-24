package com.restaurant.app.CompanyTable.service;

import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.Company.repository.CompanyRepository;
import com.restaurant.app.CompanyTable.entity.CompanyTable;
import com.restaurant.app.CompanyTable.repository.CompanyTableRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyTableServiceImp implements CompanyTableService{

    CompanyTableRepository companyTableRepository;
    CompanyRepository companyRepository;

    @Override
    public CompanyTable getCompanyTableByNumberAndCompanyId(int tableNumber, @NonNull Long companyId) {
        //Valido si los datos son validos
        if(isNotValidTableNumber(tableNumber))
            throw new IllegalArgumentException("Table number must be greater than 0");

        //Valido id compania
        if(isNotValidCompanyId(companyId))
            throw new IllegalArgumentException("Company id must be greater than 0");

        //Busco la mesa
        CompanyTable table = companyTableRepository.findByNumberAndCompanyId(tableNumber, companyId);

        if( table == null)
            throw new IllegalArgumentException("Table with number " + tableNumber + " doesn't exist in company with " +
                    "id " + companyId);

        //Si son existe la mesa, la retorno
        return table;
    }

    @Override
    public CompanyTable createCompanyTable(@NonNull Long companyId, @NonNull CompanyTable table) {
        //Valido la informacion de la mesa
        //Numero de mesa mayor a 0
        if(isNotValidTableNumber(table.getNumber()))
            throw new IllegalArgumentException("Table number must be greater or equal than 1");

        //Asientos entre 1 y 10
        if(isNotValidSeats(table.getSeats()))
            throw new IllegalArgumentException("Table seats must be between 1 and 10");

        //Ubicacion valida
        if(isNotValidTableLocation(table.getLocation().name()))
            throw new IllegalArgumentException("Table location must be OUTDOOR or INDOOR");

        //Estado valido
        if(isNotValidTableStatus(table.getStatus().name()))
            throw new IllegalArgumentException("Table status must be AVAILABLE, OCCUPIED or RESERVED");

        //Si existe la mesa con el mismo numero y pertenece a la misma compania, lanzo excepcion
        if(companyTableRepository.findByNumberAndCompanyId(table.getNumber(), companyId) == null)
            throw new EntityExistsException("Table with number " + table.getNumber() + " already exists in company with id " + companyId);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company with id " + companyId + " doesn't exist"));

        //Si no existe y los datos son validos, la creo
        table.setCompany(company);
        return companyTableRepository.save(table);

    }

    @Override
    public CompanyTable updateCompanyTable(@NonNull Long companyId, @NonNull CompanyTable table) {

        //Valido si existe una mesa con el mismo numero en la compania y
        // si tiene el mismo id que la mesa a actualizar
        CompanyTable existingTable = companyTableRepository.findByNumberAndCompanyId(table.getNumber(), companyId);

        if(existingTable != null && !existingTable.getId().equals(table.getId()))
            throw new IllegalArgumentException("Table with number " + table.getNumber() + " already exists in company with id " + companyId);


        //Valido que los datos ingresados sean validos
        if(isNotValidCompanyId(companyId))
            throw new IllegalArgumentException("Company id must be greater than 0");

        if(isNotValidSeats(table.getSeats()))
            throw new IllegalArgumentException("Table seats must be between 1 and 10");

        if(isNotValidTableLocation(table.getLocation().name()))
            throw new IllegalArgumentException("Table location must be OUTDOOR or INDOOR");

        if(isNotValidTableStatus(table.getStatus().name()))
            throw new IllegalArgumentException("Table status must be AVAILABLE, OCCUPIED or RESERVED");


        //Si los datos son validos, actualizo la mesa
        CompanyTable tableToUpdate = companyTableRepository.findById(table.getId())
                .orElseThrow(() -> new IllegalArgumentException("Table with id " + table.getId() + " doesn't exist"));

        tableToUpdate.setNumber(table.getNumber());
        tableToUpdate.setSeats(table.getSeats());
        tableToUpdate.setLocation(table.getLocation());
        tableToUpdate.setStatus(table.getStatus());

        return companyTableRepository.save(tableToUpdate);
    }

    @Override
    public void deleteCompanyTable(@NonNull Long companyId, int tableNumber) {

        if(isNotValidCompanyId(companyId))
            throw new IllegalArgumentException("Company id must be greater than 0");

        if(isNotValidTableNumber(tableNumber))
            throw new IllegalArgumentException("Table number must be greater than 0");

        //Si los datos son validos, busco si existe la mesa
        CompanyTable table = companyTableRepository.findByNumberAndCompanyId(tableNumber, companyId);

        //Si no se encontro mesa, lanzo excepcion
        if(table == null)
            throw new IllegalArgumentException("Table with number " + tableNumber + " doesn't exist in company with " +
                    "id " + companyId);

        //Si la mesa no esta disponible, no la puedo eliminar
        if(isNotValidToDelete(table))
            throw new IllegalArgumentException("Table with number " + tableNumber + " is not available to be deleted");

        //Si existe, la elimino
        companyTableRepository.delete(table);
    }

    @Override
    public List<CompanyTable> getCompanyTablesByCompanyId(@NonNull Long companyId) {
        //Valido que el id de la compania sea valido
        if(isNotValidCompanyId(companyId))
            throw new IllegalArgumentException("Company id must be greater than 0");

        return companyTableRepository.findByCompanyId(companyId);
    }



    //Utils para validar datos
    private boolean isNotValidTableLocation(String location){
        return location == null || !(location.equals("INDOOR") || location.equals("OUTDOOR"));
    }
    private boolean isNotValidTableStatus(String status){
        return status == null || !(status.equals("AVAILABLE") || status.equals("OCCUPIED") || status.equals("RESERVED"));
    }
    private boolean isNotValidSeats(int seats){
        return seats < 1 || seats > 10;
    }
    private boolean isNotValidTableNumber(int number){
        return number < 1;
    }
    private boolean isNotValidCompanyId(Long companyId){
        return companyId == null || companyId < 1;
    }

    private boolean isNotValidToDelete(CompanyTable table){
        return table.getStatus() != null && !table.getStatus().name().equals("AVAILABLE");
    }
}
