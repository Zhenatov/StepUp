package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.Products;
import org.example.reposiory.ProductsRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

//    public Products getProductById(Long id) throws SQLException {
//        return productsRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new SQLException("Product not found"));
//    }
//
//    public List<Products> getAllProducts(Long userId) throws SQLException {
//        return productsRepository.findByUsersId(userId).orElseThrow(() -> new SQLException("Product not found"));
//    }
}
