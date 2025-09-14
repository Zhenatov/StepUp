package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Table(name = "products")
@RequiredArgsConstructor
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "balance")
    private Double balance;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private Users user;

    enum ProductType{
        ACCOUNT, CARD
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", productType=" + productType.toString() +
                ", createdAt=" + createdAt +
                '}';
    }
}
