package org.example.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.ProductsDto;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/payment")
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/search/all-product")
    public List<ProductsDto> searchProduct(@RequestParam(name = "userId") Long userId) {
        log.info("Search product by userId: {}", userId);
        return paymentService.getProductsById(userId);
    }
}
