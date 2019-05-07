package learnspringboot.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/*
  JPA defines a spec to map Java objects to data from DB
  JPA 2.1 implementation: Hibernate which support a lot of databases
*/

@Entity // Prefer from javax (JPA) rather than Hibernate
@Table(name = "product") // Specify table name if different from entity name
@Getter
@Setter
public class Product {

    // Id property from Java class is a leakage to data
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    private String name;

    private String make;

    private Double price;
}
