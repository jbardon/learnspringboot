package learnspringboot.core.dto;

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
}
