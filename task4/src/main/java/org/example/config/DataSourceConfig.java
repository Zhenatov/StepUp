package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/Task4?currentSchema=users");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("postgres");
        hikariDataSource.setMaximumPoolSize(10);
        return hikariDataSource;
    }
}
