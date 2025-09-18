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

    public List<ProductsDto> getAllProducts(Long userId) {
        log.info("Запрос продукта с userId: {}", userId);
        List<Long> paymentsId = paymentsRepository.findByUserId(userId).orElseThrow(
                () -> {
                    log.error("У пользователя с id = {} не найдены платежи", userId);
                    return new EntityNotFoundException("Платежи не найдены, userId: " + userId);
                }
        )
                .stream()
                .map(Payment::getId)
                .toList();
        List<Products> productsList = productsRepository.findByPaymentIdIn(paymentsId).orElseThrow(() -> {
            log.error("Продукты по данным платежам не найдены {} не найден: ", paymentsId);
            return new EntityNotFoundException("Продукты не найден, paymentsId: " + paymentsId);
        });
        return productsMapper.toProductsDtoList(productsList);
    }
}
