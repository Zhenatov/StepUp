package org.example.mapper;

import org.example.model.dto.ProductsDto;
import org.example.model.entity.Products;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProductsMapper {

    public ProductsDto toProductsDto(Products products) {
        return new ProductsDto(products.getId(), products.getAccountNumber(), products.getBalance(),String.valueOf(products.getProductType()));
    }

    public List<ProductsDto> toProductsDtoList(List<Products> productsList) {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        if (!productsList.isEmpty()) {
            for (Products products : productsList) {
                productsDtoList.add(toProductsDto(products));
            }
        }
        return productsDtoList;
    }

    public Products toEntity(ProductsDto productsDto) {
        return new Products()
                .setAccountNumber(productsDto.accountNumber())
                .setBalance(productsDto.balance())
                .setCreatedAt(OffsetDateTime.now())
                .setProductType(Products.ProductType.valueOf(productsDto.productType()));
    }

    public Set<Products> toEntitySet(List<ProductsDto> productsDtoList) {
        Set<Products> productsList = new HashSet<>();
        if (!productsDtoList.isEmpty()) {
            for (ProductsDto productsDto : productsDtoList) {
                productsList.add(toEntity(productsDto));
            }
        }
        return productsList;
    }
}
