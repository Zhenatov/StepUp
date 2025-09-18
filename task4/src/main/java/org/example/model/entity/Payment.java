package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.example.Utils.Utils.setToString;

@Entity
@Table(name = "payments")
@RequiredArgsConstructor
@Getter
@NamedEntityGraph(
        name = "Payment.withProducts",
        attributeNodes = @NamedAttributeNode("products")
)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sum")
    private Double sum;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private Users user;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Products> products;



    public Payment setId(Long id) {
        this.id = id;
        return this;
    }

    public Payment setSum(Double sum) {
        this.sum = sum;
        return this;
    }

    public Payment setUser(Users user) {
        this.user = user;
        return this;
    }

    public Payment setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Payment setProducts(Set<Products> products) {
        this.products = products;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Payments [id=").append(id)
                .append(", sum=").append(sum)
                .append(", createdAt=").append(createdAt)
                .append(", products=").append(setToString(products))
                .append("]");

        return sb.toString();
    }

    public void addProduct(Products product) {
        products.add(product);
        product.setPayment(this);
    }
}
