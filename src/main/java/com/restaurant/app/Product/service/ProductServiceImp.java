package com.restaurant.app.Product.service;

import com.restaurant.app.Product.dto.ProductDTO;
import com.restaurant.app.Product.entity.Product;
import com.restaurant.app.Product.entity.ProductMapper;
import com.restaurant.app.Product.repository.ProductRepository;
import com.restaurant.app.security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
    public List<ProductDTO> getAllProducts ()
    {
        final String token = request.getHeader("Authorization").split(" ")[1];
        final Claims claims = jwtService.extractAllClaims(token);

        Long companyId = claims.get("companyId", Long.class);

        return productRepository.findByCompanyId(companyId)
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    @Override
    public ProductDTO getProductById (Long id)
    {
        return productRepository
                .findById(id)
                .map(ProductMapper::toDTO)
                .orElseThrow(() -> new RuntimeException(("Product not found with id: " + id)));
    }

    @Override
    public ProductDTO createProduct (Product product)
    {
        Product prod = productRepository.findByNameIgnoreCaseAndCompanyId(product.getName(), product.getCompany().getId());
        if(prod != null){
            throw new RuntimeException("Product already exists");
        }

        return ProductMapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct (Long id, Product product)
    {
        return ProductMapper.toDTO(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)));
//               return productRepository.findById(id).
//                map(productDb -> {
//                   productDb.setName(product.getName());
//                   productDb.setDescription(product.getDescription());
//                   productDb.setPrice(product.getPrice());
//                   productDb.setStock(product.getStock());
//                   productDb.setCategory(product.getCategory());
//                   productDb.setSubCategory(product.getSubCategory());
//                   productDb.setCompany(product.getCompany());
//                   return productRepository.save(productDb);
//               })
//                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStock(), p.getCategory().getName(), p.getSubCategory().getName()))
//               .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct (Long id)
    {

        Product product = productRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductsByCategoryId (Long categoryId)
    {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getProductsBySubCategoryId (Long subCategoryId)
    {
        return productRepository.findBySubCategoryId(subCategoryId)
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getProductsByName (String name)
    {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }
}
