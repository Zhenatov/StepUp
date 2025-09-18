package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.UsersMapper;
import org.example.model.dto.UsersDto;
import org.example.model.entity.Users;
import org.example.reposiory.UsersRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Transactional
    public UsersDto getUserById(Long id) {
        log.info("Запрос пользователя с ID: {}", id);
        Users user = usersRepository.findById(id).orElseThrow(() -> {
            log.error("Пользователя с ID не найден: {}", id);
            return new EntityNotFoundException("Пользователь не найден ID: " + id);
        });
        return usersMapper.toUsersDto(user);
    }

    @Transactional
    public UsersDto getUserByUsername(String username) {
        log.info("Запрос пользователя с username: {}", username);
        Users user = usersRepository.findByUsername(username).orElseThrow(() -> {
            log.error("Пользователя с username не найден: {}", username);
            return new EntityNotFoundException("Пользователь не найден username: " + username);
        });
        return usersMapper.toUsersDto(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    @Transactional
    public void deleteUserByUserName(String userName) {
        usersRepository.deleteByUsername(userName);
    }

    @Transactional
    public List<UsersDto> getAllUsers() {
        return usersMapper.toUsersDtoList(usersRepository.findAll());
    }

    @Transactional
    public void deleteAllUsers() {
        usersRepository.deleteAll();
    }

    @Transactional
    public void updateUser(Users user) {
        usersRepository.save(user);
    }
}
