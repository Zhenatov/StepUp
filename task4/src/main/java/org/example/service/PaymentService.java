package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.PaymentMapper;
import org.example.mapper.ProductsMapper;
import org.example.model.dto.PaymentDto;
import org.example.model.entity.Payment;
import org.example.model.entity.Products;
import org.example.model.entity.Users;
import org.example.reposiory.PaymentsRepository;
import org.example.reposiory.ProductsRepository;
import org.example.reposiory.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final UsersRepository usersRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentsRepository paymentsRepository;
    private final ProductsMapper productsMapper;

    public void pay(PaymentDto paymentDto) {
        Users users = usersRepository.findById(paymentDto.userId()).orElseThrow(
                () -> {
                    log.error("User not found");
                    return new EntityNotFoundException("User not found");//ToDo поправить эксепшн
                }
        );

        Payment payment = paymentMapper.toEntity(paymentDto);
        Set<Products> productsList = productsMapper.toEntitySet(paymentDto.productsDtoList());
        payment.setProducts(productsList);
        for (Products products : productsList) {
            payment.addProduct(products);
        }
        users.getPayments().add(payment);
        users.addPayment(payment);
        usersRepository.save(users);
    }

    public List<PaymentDto> searchAllPaymentsByUserId(Long userId) {
        List<Payment> paymentList = paymentsRepository.findByUserId(userId).orElseThrow(
                () -> {
                    log.error("Payment not found");
                    return new EntityNotFoundException("User not found");//ToDo поправить эксепшн
                }
        );

        return paymentMapper.toDtoList(paymentList);
    }
}
