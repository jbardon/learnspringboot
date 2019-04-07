package learnspringboot.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

//@Entity // Comment when using @ElementCollection
@Embeddable // For @ElementCollection
@Getter
@Setter
public class Review {

    // Not need with @ElementCollection
    //@Id
    //@GeneratedValue(strategy = AUTO)
    //private Integer id;

    private String message;

    // Not need with @ElementCollection
    //@Column(name = "product_id")
    //private Integer productId;
}
