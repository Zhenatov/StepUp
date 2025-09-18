package org.example.config.porps;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.rest-service")
@Getter
@Setter
@RequiredArgsConstructor
public class RestTemplateConfigurationProperties {
    private RestTemplateProperties usersAndProducts;
}
