package learnspringboot.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "orders") // Don't use order as table table, it's a reserved SQL keyword
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    // Essayer enumrable string
}
