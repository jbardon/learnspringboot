package learnspringboot.core.service.converter;

import learnspringboot.core.domain.Product;
import learnspringboot.core.domain.Review;
import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.dto.ReviewDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            dto.setReviews(mapReviewsToNoView(domain.getReviews()));
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

    private List<ReviewDto> mapReviewsToNoView(final List<Review> domainList) {
        return domainList.stream()
                .map(domain -> {
                    final ReviewDto dto = new ReviewDto();
                   // dto.setId(domain.getId());
                    dto.setMessage(domain.getMessage());
                    return dto;
                }).collect(Collectors.toList());
    }
}
