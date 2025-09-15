package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.mapper.ProductsMapper;
import org.example.model.dto.ProductsDto;
import org.example.model.entity.Products;
import org.example.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    private final ProductsMapper productsMapper;

    @GetMapping("/getProductById")
    public ProductsDto getProductById(@RequestParam(name = "productId") Long productId) throws SQLException {
        return productsMapper.toProductsDto(productsService.getProductById(productId));
    }

    @GetMapping("/getAllProductByUserId")
    public List<ProductsDto> getAllProductById( @RequestParam(name = "userId") Long userId) throws SQLException {
        return productsMapper.toProductsDtoList(productsService.getAllProducts(userId));
    }
}
