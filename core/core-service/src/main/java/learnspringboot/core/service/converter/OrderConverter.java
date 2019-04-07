package learnspringboot.core.service.converter;

import learnspringboot.core.domain.Order;
import learnspringboot.core.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class OrderConverter {

    private Map<View, Function<Order, OrderDto>> convertTo;
    private Map<View, BiFunction<OrderDto, Order, Order>> convertFrom;

    public OrderConverter() {

        this.convertTo = new HashMap<>();
        this.convertFrom = new HashMap<>();

        convertTo.put(View.NO_VIEW, this::convertToNoView);
        convertFrom.put(View.NO_VIEW, this::convertFromNoView);
    }

    public OrderDto convertTo(final View view, final Order domain) {
        return convertTo.getOrDefault(view, this::convertToNoView).apply(domain);
    }

    public Order convertFrom(final View view, final OrderDto dto, final Order domain) {
        return convertFrom.getOrDefault(view, this::convertFromNoView).apply(dto, domain);
    }

    private OrderDto convertToNoView(final Order domain) {
        final OrderDto dto = new OrderDto();
        if (domain != null) {
            dto.setId(domain.getId());
        }
        return dto;
    }

    private Order convertFromNoView(final OrderDto dto, Order domain) {
        if (dto != null) {
            domain.setId(dto.getId());
        }
        return domain;
    }

    //map specific command
    // map exprès providers
}
