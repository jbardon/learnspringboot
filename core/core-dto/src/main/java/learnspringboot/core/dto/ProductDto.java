package learnspringboot.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {

    private Integer id;

    private String name;

    private Double price;

    private List<ReviewDto> reviews;
}