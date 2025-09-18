package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.porps.RestTemplateConfigurationProperties;
import org.example.config.porps.RestTemplateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableConfigurationProperties(RestTemplateConfigurationProperties.class)
@RequiredArgsConstructor
@Slf4j
public class AppPayConfig {

    private final RestTemplateConfigurationProperties restTemplateConfigurationProperties;


    @Bean("usersAndProductsRestTemplate")
    public RestTemplate usersAndProductsRestTemplate() {
        RestTemplateProperties product =  restTemplateConfigurationProperties
                .getUsersAndProducts();

        return new RestTemplateBuilder()
                .rootUri(product.getUrl())
                .readTimeout(product.getReadTimeout())
                .connectTimeout(product.getConnectTimeout())
                .additionalInterceptors((request, body, execution) -> {
                    log.info("Request to URI: {}", request.getURI());
                    return execution.execute(request, body);
                })
                .build();
    }
}
