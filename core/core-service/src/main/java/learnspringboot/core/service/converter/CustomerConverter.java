package learnspringboot.core.service.converter;

import learnspringboot.core.domain.Customer;
import learnspringboot.core.domain.Product;
import learnspringboot.core.dto.CustomerDto;
import learnspringboot.core.dto.CustomerDto;
import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.service.converter.utils.View;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    private Map<View, Function<Customer, CustomerDto>> convertTo;
    private Map<View, BiFunction<CustomerDto, Customer, Customer>> convertFrom;

    public CustomerConverter() {
        this.convertTo = new HashMap<>();
        this.convertFrom = new HashMap<>();

        convertTo.put(View.NO_VIEW, this::convertToNoView);
        convertFrom.put(View.NO_VIEW, this::convertFromNoView);
    }

    public CustomerDto convertTo(final View view, final Customer domain) {
        return convertTo.getOrDefault(view, this::convertToNoView).apply(domain);
    }

    public Customer convertFrom(final View view, final CustomerDto dto, final Customer domain) {
        return convertFrom.getOrDefault(view, this::convertFromNoView).apply(dto, domain);
    }

    private CustomerDto convertToNoView(final Customer domain) {
        final CustomerDto dto = new CustomerDto();
        if (domain != null) {
            dto.setId(domain.getId());
        }
        return dto;
    }

    private Customer convertFromNoView(final CustomerDto dto, Customer domain) {
        if (dto != null) {
            domain.setId(dto.getId());
        }
        return domain;
    }

    //map specific command
    // map exprès providers

        /*
    private List<ReviewDto> mapReviewsToNoView(final List<Review> domainList) {
        return domainList.stream()
                .map(domain -> {
                    final ReviewDto dto = new ReviewDto();
                   // dto.setId(domain.getId());
                    dto.setMessage(domain.getMessage());
                    return dto;
                }).collect(Collectors.toList());
    }
    */
}
