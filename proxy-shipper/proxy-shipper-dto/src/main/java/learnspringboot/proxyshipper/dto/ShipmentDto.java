package learnspringboot.proxyshipper.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ShipmentDto {

    private Integer id;

    private String companyName;

    private Date beginDate;

    private Date endDate;
}
