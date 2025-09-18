package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.example.Utils.Utils.setToString;

@Getter
@Entity
@Table(name = "users")
@NamedEntityGraph(
        name = "Users.withPayments",
        attributeNodes = @NamedAttributeNode("payments")
)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name ="username", unique = true, nullable = false)
    private String username;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Payment> payments;


    public Users setId(Long id) {
        this.id = id;
        return this;
    }

    public Users setUsername(String username) {
        this.username = username;
        return this;
    }

    public Users setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Users setPayments(Set<Payment> payments) {
        this.payments = payments;
        return this;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Users [id=").append(id)
                .append(", username=").append(username)
                .append(", createdAt=").append(createdAt)
                .append(", payments=").append(setToString(payments))
                .append("]").toString();
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setUser(this);
    }
}
