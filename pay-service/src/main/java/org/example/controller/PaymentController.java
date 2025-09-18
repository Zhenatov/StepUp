package org.example.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.PaymentDto;
import org.example.model.dto.ProductsDto;
import org.example.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/search/all-product")
    public List<ProductsDto> searchProduct(@RequestParam(name = "userId") Long userId) {
        log.info("Search product by userId: {}", userId);
        return paymentService.getProductsById(userId);
    }

    @PostMapping("/pay")
    public ResponseEntity<Void> pay(@RequestBody PaymentDto paymentDto) {
        log.info("Pay product : paymentDto = {}", paymentDto);
        paymentService.pay(paymentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/all-payments")
    public List<PaymentDto> searchAllPaymentsByUserId(@RequestParam(name = "userId") Long userId) {
        log.info("Search all payments by userId: {}", userId);
        return paymentService.searchAllPaymentsByUserId(userId);
    }
}
