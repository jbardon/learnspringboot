package learnspringboot.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
public class Shipment {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    private String companyName;

    private Date beginDate;

    private Date endDate;
}
