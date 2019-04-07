package learnspringboot.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

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
    private Integer uniqueId;

    private String fullName;

    private String manufacturer;

    private Double price;

    @Column(name = "provider_id")
    private Integer providerId;

    /*
        First way: not very coupled, reviews can exist without a product
        Must specify cascade and orphan attribute to decide operation propagation (such as delete)
        @OneToMany
        @JoinColumn(name = "product_id")
    */
    /*
        Second way: tightly coupled, a review cannot exist without a product
        Can specify column to autoselect:  @Column(name="message")
      */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="review", joinColumns=@JoinColumn(name="product_id"))
    private List<Review> reviews;
}
