package learnspringboot.core.service.converter;

import learnspringboot.core.domain.Order;
import learnspringboot.core.domain.Product;
import learnspringboot.core.dto.CustomerDto;
import learnspringboot.core.dto.OrderDto;
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
public class OrderConverter {

    private final ProductConverter productConverter;

    private Map<View, BiFunction<Order, CustomerDto, OrderDto>> convertTo;
    private Map<View, BiFunction<OrderDto, Order, Order>> convertFrom;

    public OrderConverter(final ProductConverter productConverter) {

        this.productConverter = productConverter;

        this.convertTo = new HashMap<>();
        this.convertFrom = new HashMap<>();

        convertTo.put(View.NO_VIEW, this::convertToNoView);
        convertFrom.put(View.NO_VIEW, this::convertFromNoView);
    }

    public OrderDto convertTo(final View view, final Order domain,
                              final Function<Integer, CustomerDto> customerProvider)
                              //final Function<Integer, ShipmentDto> shipmentProvider)
    {
        final CustomerDto customer = customerProvider.apply(domain.getCustomerId());

        // Troubles, comment faire ? Pas de TriFunction
        //final ShipmentDto shipment = shipmentProvider.apply(domain.getId());
        return convertTo.getOrDefault(view, this::convertToNoView).apply(domain, customer);
    }

    public Order convertFrom(final View view, final OrderDto dto, final Order domain) {
        return convertFrom.getOrDefault(view, this::convertFromNoView).apply(dto, domain);
    }

    private OrderDto convertToNoView(final Order domain,
                                     final CustomerDto customerDto) {
        final OrderDto dto = new OrderDto();
        if (domain != null) {
            dto.setId(domain.getId());
            dto.setStatus(domain.getStatus().name());
            dto.setCustomer(customerDto);
            dto.setProducts(mapToProducts(domain.getProducts()));
        }
        return dto;
    }

    private Order convertFromNoView(final OrderDto dto, Order domain) {
        if (dto != null) {
            domain.setId(dto.getId());
        }
        return domain;
    }


    private List<ProductDto> mapToProducts(final List<Product> domain) {
        return domain.stream()
                .map(p -> productConverter.convertTo(View.NO_VIEW, p))
                .collect(Collectors.toList());
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
