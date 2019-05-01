package learnspringboot.core.domain;

import learnspringboot.core.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "orders") // Don't use order as table table, it's a reserved SQL keyword
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    @Enumerated(STRING)
    private OrderStatus status;

    @ManyToMany
    @JoinTable(
        name = "orders_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @Column(name = "customer_id")
    private Integer customerId;
}
