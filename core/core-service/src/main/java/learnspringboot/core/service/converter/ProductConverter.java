package learnspringboot.core.service.converter;

import learnspringboot.core.domain.Product;
import learnspringboot.core.dto.ProductDto;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ProductConverter {

    private Map<View, Function<Product, ProductDto>> convertTo;
    private Map<View, BiFunction<ProductDto, Product, Product>> convertFrom;

    public ProductConverter() {
        convertTo.put(View.NO_VIEW, this::convertToNoView);
        convertFrom.put(View.NO_VIEW, this::convertFromNoView);
    }

    // add providers here
    public ProductDto convertTo(final View view, final Product domain) {
        return convertTo.getOrDefault(view, this::convertToNoView).apply(domain);
    }

    public Product convertFrom(final View view, final ProductDto dto, final Product domain) {
        return convertFrom.getOrDefault(view, this::convertFromNoView).apply(dto, domain);
    }

    private ProductDto convertToNoView(final Product domain) {
        final ProductDto dto = new ProductDto();
        if (domain != null) {
            dto.setId(domain.getUniqueId());
            dto.setName(domain.getFullName());
            dto.setPrice(domain.getPrice());
        }
        return dto;
    }

    private Product convertFromNoView(final ProductDto dto, Product domain) {
        if (dto != null) {
            domain.setUniqueId(dto.getId());
            domain.setFullName(dto.getName());
            domain.setPrice(dto.getPrice());
        }
        return domain;
    }
}
