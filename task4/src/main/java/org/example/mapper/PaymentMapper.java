package org.example.mapper;

import org.example.model.dto.PaymentDto;
import org.example.model.dto.ProductsDto;
import org.example.model.entity.Payment;
import org.example.model.entity.Products;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PaymentMapper {
    private final ProductsMapper productsMapper;

    public PaymentMapper(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    public Payment toEntity(PaymentDto paymentDto){
        return new Payment()
                .setSum(paymentDto.sum())
                .setCreatedAt(OffsetDateTime.now());
    }

    public Set<Payment> toEntitySet(List<PaymentDto> paymentDtoList){
        Set<Payment> payment = new HashSet<>();
        if(!paymentDtoList.isEmpty()){
            for(PaymentDto paymentDto : paymentDtoList){
                payment.add(toEntity(paymentDto));
            }
        }
        return payment;
    }


    public PaymentDto toDto(Payment payment){
        List<ProductsDto> productsDtoList = new ArrayList<>();
        if(!payment.getProducts().isEmpty()){
            productsDtoList = productsMapper.toProductsDtoList(payment.getProducts().stream().toList());
        }

        return new PaymentDto(payment.getId(), productsDtoList, payment.getSum(), payment.getUser().getId());
    }

    public List<PaymentDto> toDtoList(List<Payment> paymentList){
        List<PaymentDto> paymentDtoList = new ArrayList<>();
        if(!paymentList.isEmpty()){
            for(Payment payment : paymentList){
                paymentDtoList.add(toDto(payment));
            }
        }
        return paymentDtoList;
    }
}
