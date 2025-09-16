package org.example.reposiory;

import org.example.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Optional<List<Products>> findByUserId(Long userId);
}
