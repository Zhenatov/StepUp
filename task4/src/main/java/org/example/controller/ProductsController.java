package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.ProductsDto;
import org.example.service.ProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping("/search/id")
    public ProductsDto getProductById(@RequestParam(name = "id") Long productId) {
        return productsService.getProductById(productId);
    }

    @GetMapping("/search/user-id")
    public List<ProductsDto> getAllProductById(@RequestParam(name = "userId") Long userId) {
        return productsService.getAllProducts(userId);
    }
}
