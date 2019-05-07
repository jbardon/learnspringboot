package learnspringboot.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Integer id;

    private String name;

    private String make;

    private Double price;
}
