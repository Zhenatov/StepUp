package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.PaymentDto;
import org.example.model.dto.ProductsDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    @Qualifier("usersAndProductsRestTemplate")
    private final RestTemplate restTemplate;

    private final String ENDPOINT_PRODUCTS = "/v1/products";
    private final String ENDPOINT_USERS = "/v1/users";
    private final String ENDPOINT_PAYMENT = "/v1/payment";
    private final String ENDPOINT_SEARCH = "/search";
    private final String ENDPOINT_PAY = "/pay";
    private final String ENDPOINT_ALL_PAYMENTS = "/all-payments";

    public List<ProductsDto> getProductsById(Long userId) {
        String url = String.format("%s%s/user-id?userId=%d", ENDPOINT_PRODUCTS, ENDPOINT_SEARCH, userId);
        log.info("getProductsById → {}", url);

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductsDto>>() {}
        ).getBody();
    }


    public void pay(PaymentDto paymentDto) {
        String url = String.format("%s%s", ENDPOINT_PAYMENT, ENDPOINT_PAY, paymentDto.userId());
        log.info("test");

        if(paymentDto.productsDtoList().isEmpty()){
            throw new EntityNotFoundException();//ToDo поправить эксепшн
        }
        Double orderAmount = 0.0;

        for(ProductsDto productsDto : paymentDto.productsDtoList()){
            orderAmount += productsDto.balance();
        }

        if(paymentDto.sum() < orderAmount){
            throw new EntityNotFoundException();//ToDo поправить эксепшн
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto, headers);

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<List<ProductsDto>>() {}
        ).getBody();
    }

    public List<PaymentDto> searchAllPaymentsByUserId(Long userId) {
        String url = String.format("%s%s%s?userId=%d", ENDPOINT_PAYMENT, ENDPOINT_SEARCH, ENDPOINT_ALL_PAYMENTS, userId);
        log.info("test");

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PaymentDto>>() {}
        ).getBody();
    }
}
