package learnspringboot.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddressDto {
    private String street;

    private Integer zipCode;

    private String city;

    private String country;
}