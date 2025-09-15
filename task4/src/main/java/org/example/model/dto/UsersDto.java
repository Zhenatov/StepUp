package org.example.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class UsersDto {
    private Long id;
    private String username;
    private Set<ProductsDto> productsDtoSet;
}
