package learnspringboot.core.service.converter;

import learnspringboot.core.dto.OrderDto;
import learnspringboot.core.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class ConverterProviders {

    private Function<Integer, List<ProductDto>> ProductProvider;

    //List<ProductDto> ProductProvider(Integer id);

    //List<OrderDto> OrderProvider(String id);
}
