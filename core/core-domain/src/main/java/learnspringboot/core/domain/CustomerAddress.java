package learnspringboot.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable // For @ElementCollection, instead of @Entity
@Getter
@Setter
public class CustomerAddress {
    // No need with @ElementCollection
    /*
        @Id
        @GeneratedValue(strategy = AUTO)
        private Integer id;
    */

    // Not need with @ElementCollection
    /*
        @Column(name = "product_id")
        private Integer productId
    */

    private String street;

    private Integer zipCode;

    private String city;

    private String country;
}