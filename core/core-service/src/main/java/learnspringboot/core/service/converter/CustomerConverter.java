package learnspringboot.core.service.converter;

import learnspringboot.core.domain.Customer;
import learnspringboot.core.domain.CustomerAddress;
import learnspringboot.core.domain.Order;
import learnspringboot.core.domain.Product;
import learnspringboot.core.dto.*;
import learnspringboot.core.dto.CustomerDto;
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

    private final OrderConverter orderConverter;

    private Map<View, Function<Customer, CustomerDto>> convertTo;
    private Map<View, BiFunction<CustomerDto, Customer, Customer>> convertFrom;

    public CustomerConverter(final OrderConverter orderConverter) {
        this.orderConverter = orderConverter;

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
            dto.setFirstname(domain.getFirstName());
            dto.setLastname(domain.getLastName());
            dto.setAddresses(mapCustomerAddressToNoView(domain.getAddresses()));
            dto.setOrders(mapOrdersToNoView(domain.getOrders(), dto));
        }
        return dto;
    }

    private Customer convertFromNoView(final CustomerDto dto, Customer domain) {
        if (dto != null) {
            domain.setId(dto.getId());
            domain.setFirstName(dto.getFirstname());
            domain.setLastName(dto.getLastname());
        }
        return domain;
    }

    private List<CustomerAddressDto> mapCustomerAddressToNoView(final List<CustomerAddress> domainList) {
        return domainList.stream()
                .map(domain -> {
                    final CustomerAddressDto dto = new CustomerAddressDto();
                    dto.setStreet(domain.getStreet());
                    dto.setCity(domain.getCity());
                    dto.setZipCode(domain.getZipCode());
                    dto.setCountry(domain.getCountry());
                    return dto;
                }).collect(Collectors.toList());
    }

    // FIXME: Not sure about passing unfinished CustomerDto
    private List<OrderDto> mapOrdersToNoView(final List<Order> domainList, final CustomerDto customerDto) {
        return domainList.stream()
                .map(domain -> orderConverter.convertTo(View.NO_VIEW, domain, (customerId) -> customerDto))
                .collect(Collectors.toList());
    }
}
