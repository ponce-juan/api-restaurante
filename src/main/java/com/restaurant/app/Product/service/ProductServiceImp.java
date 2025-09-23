package com.restaurant.app.Product.service;

import com.restaurant.app.Product.entity.Product;
import com.restaurant.app.Product.repository.ProductRepository;
import com.restaurant.app.security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService
{
    // Inyeccion de dependencias
    private final ProductRepository productRepository;
    private final JwtService jwtService;
    private final HttpServletRequest request;

    @Override
    public List<Product> getAllProducts ()
    {
        final String token = request.getHeader("Authorization").split(" ")[1];
        final Claims claims = jwtService.extractAllClaims(token);

        Long companyId = claims.get("companyId", Long.class);

        return productRepository.findByCompanyId(companyId);
    }

    @Override
    public Product getProductById (Long id)
    {
        return productRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException(("Product not found with id: " + id)));
    }

    @Override
    public Product createProduct (Product product)
    {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct (Long id, Product product)
    {
        return productRepository.findById(id)
                   .map(productDb -> {
                       productDb.setName(product.getName());
                       productDb.setDescription(product.getDescription());
                       productDb.setPrice(product.getPrice());
                       productDb.setStock(product.getStock());
                       productDb.setCategory(product.getCategory());
                       productDb.setSubCategory(product.getSubCategory());
                       productDb.setCompany(product.getCompany());
                       return productRepository.save(productDb);
                   })
                   .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct (Long id)
    {

        Product product = productRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepository.delete(product);
    }

    @Override
    public List<Product> getProductsByCategoryId (Long categoryId)
    {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> getProductsBySubCategoryId (Long subCategoryId)
    {
        return productRepository.findBySubCategoryId(subCategoryId);
    }

    @Override
    public List<Product> getProductsByName (String name)
    {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
