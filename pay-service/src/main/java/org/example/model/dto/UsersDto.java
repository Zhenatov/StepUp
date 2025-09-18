package org.example.model.dto;

import java.util.List;

public record UsersDto(Long id, String username, List<PaymentDto> paymentDtoList) {}
