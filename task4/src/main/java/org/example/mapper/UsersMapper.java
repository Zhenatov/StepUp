package org.example.mapper;

import org.example.model.dto.ProductsDto;
import org.example.model.dto.UsersDto;
import org.example.model.entity.Products;
import org.example.model.entity.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersMapper {
    private final ProductsMapper productsMapper;

    public UsersMapper(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    public UsersDto toUsersDto(Users users) {
        return new UsersDto(users.getId(), users.getUsername(), productsMapper.toProductsDtoList(users.getProducts().stream().toList()));
    }

    public List<UsersDto> toUsersDtoList(List<Users> usersList) {
        List<UsersDto> usersDtoList = new ArrayList<>();
        for (Users users : usersList) {
            usersDtoList.add(toUsersDto(users));
        }
        return usersDtoList;
    }
}
