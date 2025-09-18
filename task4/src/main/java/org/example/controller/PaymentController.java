package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.PaymentDto;
import org.example.model.dto.ProductsDto;
import org.example.service.PaymentService;
import org.example.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Void> pay(@RequestBody PaymentDto paymentDto) {
        log.info("Payment received: {}", paymentDto);
        paymentService.pay(paymentDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/search/all-payments")
    public List<PaymentDto> searchAllPaymentsByUserId(@RequestParam(name = "userId") Long userId) {
        log.info("Search all payments by userId: {}", userId);
        return paymentService.searchAllPaymentsByUserId(userId);
    }
}
