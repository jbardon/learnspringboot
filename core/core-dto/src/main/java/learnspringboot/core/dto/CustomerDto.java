package learnspringboot.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private List<OrderDto> orders;

    private List<CustomerAddressDto> addresses;
}
