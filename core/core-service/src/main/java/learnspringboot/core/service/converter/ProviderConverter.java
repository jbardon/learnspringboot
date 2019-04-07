package learnspringboot.core.service.converter;

import learnspringboot.core.domain.Provider;
import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.dto.ProviderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class ProviderConverter {

    private Map<View, BiFunction<Provider, List<ProductDto>, ProviderDto>> convertTo;
    private Map<View, BiFunction<ProviderDto, Provider, Provider>> convertFrom;

    @Autowired
    public ProviderConverter() {
        this.convertTo = new HashMap<>();
        this.convertFrom = new HashMap<>();

        convertTo.put(View.NO_VIEW, this::convertToNoView);
        convertFrom.put(View.NO_VIEW, this::convertFromNoView);
    }

    public ProviderDto convertTo(final View view, final Provider domain, final Function<Integer, List<ProductDto>> productProvider) {
        final List<ProductDto> products = productProvider.apply(domain.getId());
        return convertTo.getOrDefault(view, this::convertToNoView).apply(domain, products);
    }

    public Provider convertFrom(final View view, final ProviderDto dto, final Provider domain) {
        return convertFrom.getOrDefault(view, this::convertFromNoView).apply(dto, domain);
    }

    private ProviderDto convertToNoView(final Provider domain, final List<ProductDto> products) {
        final ProviderDto dto = new ProviderDto();
        if (domain != null) {
            dto.setId(domain.getId());
            dto.setProducts(products);
        }
        return dto;
    }

    private Provider convertFromNoView(final ProviderDto dto, Provider domain) {
        if (dto != null) {
            domain.setId(dto.getId());
        }
        return domain;
    }

    /*
    private List<ProductDto> mapToProducts(final List<Product> products) {
        return products.stream()
                .map(p -> productConverter.convertTo(View.NO_VIEW, p))
                .collect(Collectors.toList());
    }
    */

    //map specific command
    // map exprès providers
}
