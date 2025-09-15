package org.example.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.example.model.entity.Products;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class ProductsDto {
    private Long id;
    private Long accountNumber;
    private Double balance;
    private String productType;
}
