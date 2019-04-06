package learnspringboot.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

/*
  JPA defines a spec to map Java objects to data from DB
  JPA 2.1 implementation: Hibernate which support a lot of databases
*/

@Entity // Prefer from javax (JPA) rather than Hibernate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    // Id property from Java class is a leakage to data
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer uniqueId;

    private String fullName;

    private String manufacturer;

    private Double price;
}
