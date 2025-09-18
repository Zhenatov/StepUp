package org.example.mapper;

import org.example.model.dto.PaymentDto;
import org.example.model.dto.UsersDto;
import org.example.model.entity.Users;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersMapper {
    private final PaymentMapper paymentMapper;

    public UsersMapper(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    public UsersDto toUsersDto(Users users) {
        List<PaymentDto> paymentDtoList = null;
        if (!users.getPayments().isEmpty()) {
            paymentDtoList = paymentMapper.toDtoList(users.getPayments().stream().toList());
        }

        return new UsersDto(users.getId(), users.getUsername(), paymentDtoList);
    }

    public List<UsersDto> toUsersDtoList(List<Users> usersList) {
        List<UsersDto> usersDtoList = new ArrayList<>();
        if (!usersList.isEmpty()) {
            for (Users users : usersList) {
                usersDtoList.add(toUsersDto(users));
            }
        }

        return usersDtoList;
    }

    public Users toEntity(UsersDto usersDto) {
        return new Users()
                .setUsername(usersDto.username())
                .setCreatedAt(OffsetDateTime.now());
    }

}
