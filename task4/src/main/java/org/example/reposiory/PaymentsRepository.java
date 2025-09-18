package org.example.reposiory;

import org.example.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    Optional<List<Payment>> findByUserId(Long userId);
}
