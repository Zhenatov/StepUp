package org.example.model.dto;

import java.util.List;

public record PaymentDto(Long id, List<ProductsDto> productsDtoList, Double sum, Long userId) {
}
