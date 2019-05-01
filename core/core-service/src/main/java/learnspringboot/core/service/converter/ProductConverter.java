package learnspringboot.core.service.converter;

import learnspringboot.core.domain.Product;
import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.service.converter.utils.View;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class ProductConverter {

    private Map<View, Function<Product, ProductDto>> convertTo;
    private Map<View, BiFunction<ProductDto, Product, Product>> convertFrom;

    public ProductConverter() {
        this.convertTo = new HashMap<>();
        this.convertFrom = new HashMap<>();

        convertTo.put(View.NO_VIEW, this::convertToNoView);
        convertFrom.put(View.NO_VIEW, this::convertFromNoView);
    }

    public ProductDto convertTo(final View view, final Product domain) {
        return convertTo.getOrDefault(view, this::convertToNoView).apply(domain);
    }

    public Product convertFrom(final View view, final ProductDto dto, final Product domain) {
        return convertFrom.getOrDefault(view, this::convertFromNoView).apply(dto, domain);
    }

    private ProductDto convertToNoView(final Product domain) {
        final ProductDto dto = new ProductDto();
        if (domain != null) {
            dto.setId(domain.getId());
            dto.setName(domain.getName());
            dto.setPrice(domain.getPrice());
        }
        return dto;
    }

    private Product convertFromNoView(final ProductDto dto, Product domain) {
        if (dto != null) {
            domain.setId(dto.getId());
            domain.setName(dto.getName());
            domain.setPrice(dto.getPrice());
        }
        return domain;
    }
}
