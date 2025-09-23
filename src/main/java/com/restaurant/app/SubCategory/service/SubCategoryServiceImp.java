package com.restaurant.app.SubCategory.service;

import com.restaurant.app.SubCategory.entity.SubCategory;
import com.restaurant.app.SubCategory.repository.SubCategoryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImp implements SubCategoryService
{
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryServiceImp (SubCategoryRepository subCategoryRepository)
    {
        this.subCategoryRepository = subCategoryRepository;
    }


    @Override
    public List<SubCategory> getAllSubCategories ()
    {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory getSubCategoryById (@NonNull Long id)
    {
        return subCategoryRepository.findById(id)
                   .orElseThrow( () -> new EntityNotFoundException("SubCategory not found with id: " + id));
    }

    @Override
    public SubCategory createSubCategory (@NonNull SubCategory subCategory)
    {
        //Verifico si existe la subcategoria con el mismo nombre
        subCategoryRepository.findByNameIgnoreCase(subCategory.getName())
                             .ifPresent( existingSubCategory -> {
                                 throw new EntityExistsException("SubCategory with name " + subCategory.getName() + " already exists");
                             });

        return subCategoryRepository.save(subCategory);
    }

    @Transactional
    @Override
    public SubCategory updateSubCategory (@NonNull Long id, @NonNull SubCategory subCategory)
    {
        //Verifico si existe una subcategoria con el mismo nombre
        subCategoryRepository.findByNameIgnoreCase(subCategory.getName())
                             .ifPresent( existingSubCategory -> {
                                 throw new EntityExistsException("SubCategory with name " + subCategory.getName() + " already exists");
                             });
        //Verifico si existe la subcategoria con el id
        SubCategory subCategoryDb = subCategoryRepository.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException("SubCategory not found with id: " + id));

        //Actualizo la subcategoria
        subCategoryDb.setName(subCategory.getName());
        return subCategoryRepository.save(subCategoryDb);

    }

    @Override
    public void deleteSubCategory (@NonNull Long id)
    {
        //Verifico si existe la subcategoria con el id
        SubCategory subCategory = subCategoryRepository.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException("SubCategory not found with id: " + id));

        //Elimino la subcategoria
        subCategoryRepository.delete(subCategory);
    }
}
