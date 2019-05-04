package learnspringboot.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    /*
        First way: not very coupled, reviews can exist without a product
        Must specify cascade and orphan attribute to decide operation propagation (such as delete)
    */
    // FIXME: Infinite recursion Get Order -> Customer -> Orders -> Customer
    //@OneToMany
    //@JoinColumn(name = "customer_id")
    //private List<Order> orders;

    /*
        Second way: tightly coupled, a review cannot exist without a product
        Can specify column to autoselect:  @Column(name="message")
      */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="customer_address", joinColumns=@JoinColumn(name="customer_id"))
    private List<CustomerAddress> addresses;
}
