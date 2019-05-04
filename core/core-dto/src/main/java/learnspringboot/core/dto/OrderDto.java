package learnspringboot.core.dto;

import learnspringboot.proxyshipper.dto.ShipmentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Integer id;

    private String status;

    private List<ProductDto> products;

    private CustomerDto customer;

    // FIXME: Must convert response from proxy-shipper
    private ShipmentDto shipment;
}
