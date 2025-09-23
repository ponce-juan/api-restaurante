package com.restaurant.app.Category.service;

import com.restaurant.app.Category.entity.Category;
import com.restaurant.app.Category.repository.CategoryRepository;
import com.restaurant.app.Utils.StringUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService
{
    //Inyeccion de dependencia de Repository
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp (CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    //Get all categories
    @Override
    public List<Category> getAllCategories ()
    {
        //Retorno la lista de categorias
        return categoryRepository
                   .findAll();
    }

    //Get category by id
    @Override
    public Category getCategoryById (@NonNull Long id)
    {
        //Obtengo la categoria de la db, si no existe lanzo error.
        return categoryRepository
               .findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    //Create category
    @Override
    public Category createCategory (@NonNull Category category)
    {
        //Obtengo la categoria por el nombre
        Optional<Category> categoryDb = categoryRepository.findByNameContainingIgnoreCase(category.getName());

        if(categoryDb.isEmpty()){
            //Si la categoria no existe, la guardo. Sino retorno null
            System.out.println("Se agrego categoria" + category.getName());
            return categoryRepository.save(category);
        }
        //Si la categoria no existe, la guardo. Sino retorno null
        System.out.println("Category already exists with name:" + category.getName());
        throw new EntityExistsException("Category already exists with name: " + category.getName());
    }

    //Update category
    @Override
    public Category updateCategory (@NonNull Long id, @NonNull Category category)
    {
        //Obtengo la categoria de la db, si no existe lanza error.
        Category categoryDb = getCategoryById(id);

        //Actualizo los datos de la categoria
        categoryDb.setName(StringUtils.isNullOrBlank(category.getName())
                           ? categoryDb.getName()
                           : category.getName());

        //Guardo los cambios en la db y retorno la categoria actualizada
        return categoryRepository.save(categoryDb);
    }

    //Delete category
    @Override
    public void deleteCategory (@NonNull Long id)
    {
        //Obtengo la categoria de la db, si no existe lanza error.
        Category category = getCategoryById(id);

        //Elimino la categoria
        categoryRepository.delete(category);
    }

    //Extra operations
    //Get category by name ignore case
    @Override
    public Category getCategoryByName (@NonNull String name)
    {
        return categoryRepository
                   .findByNameContainingIgnoreCase(name)
                   .orElseThrow( () -> new EntityNotFoundException("Category not found with name: " + name));
    }
}
