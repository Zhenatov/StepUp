package org.example.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.ProductsDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
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
    private final String ENDPOINT_SEARCH = "/search";

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

}
