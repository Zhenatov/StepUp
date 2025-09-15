package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@NamedEntityGraph(
        name = "Users.withProducts",
        attributeNodes = @NamedAttributeNode("products")
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
    private Set<Products> products;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Users [id=").append(id).append(", username=").append(username).append(", createdAt=");

        if(products != null){
            sb.append(", products=").append(products.size()).append(" items");
            if(!products.isEmpty()){
                sb.append(" [");
                products.forEach(product -> sb.append(product).append(", "));
                sb.setLength(sb.length() - 2);
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
