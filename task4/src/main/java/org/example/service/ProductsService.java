package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ProductsMapper;
import org.example.model.dto.ProductsDto;
import org.example.model.entity.Payment;
import org.example.model.entity.Products;
import org.example.reposiory.PaymentsRepository;
import org.example.reposiory.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductsMapper productsMapper;
    private final PaymentsRepository paymentsRepository;

    public ProductsDto getProductById(Long id) {
        log.info("Запрос продукта с id: {}", id);
        Products products = productsRepository.findById(id).orElseThrow(() -> {
            log.error("Продукт с id не найден: {}", id);
            return new EntityNotFoundException("Продукт не найден, id: " + id);
        });
        return productsMapper.toProductsDto(products);
    }

    public List<ProductsDto> getAllProducts(Long userId) { //ToDo ошибка при получении всех продуктов по userId надо искать через список всех платежек
        log.info("Запрос продукта с userId: {}", userId);
        List<Long> payments = paymentsRepository.findByUserId(userId).orElseThrow(
                () -> {
                    log.error("Пользователь с id не найден: {}", userId);
                    return new EntityNotFoundException("Пользователь не найден, id: " + userId);
                }
        )
                .stream()
                .map(Payment::getId)
                .toList();
        List<Products> productsList = productsRepository.findByPaymentIdIn(payments).orElseThrow(() -> {
            log.error("Продукт с userId не найден: {}", userId);
            return new EntityNotFoundException("Продукт не найден, userId: " + userId);
        });
        return productsMapper.toProductsDtoList(productsList);
    }
}
