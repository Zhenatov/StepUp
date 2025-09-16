package org.example.mapper;

import org.example.model.dto.ProductsDto;
import org.example.model.entity.Products;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsMapper {

    public ProductsDto toProductsDto(Products products) {
        return new ProductsDto(products.getId(), products.getAccountNumber(), products.getBalance(),String.valueOf(products.getProductType()));
    }

    public List<ProductsDto> toProductsDtoList(List<Products> productsList) {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for (Products products : productsList) {
            productsDtoList.add(toProductsDto(products));
        }
        return productsDtoList;
    }
}
