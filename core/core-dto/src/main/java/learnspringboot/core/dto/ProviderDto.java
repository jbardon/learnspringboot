package learnspringboot.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProviderDto {

    private Integer id;

    private List<ProductDto> products;
}
