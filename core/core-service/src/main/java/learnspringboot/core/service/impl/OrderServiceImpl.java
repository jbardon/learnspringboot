package learnspringboot.core.service.impl;

import learnspringboot.core.dao.OrderRepository;
import learnspringboot.core.domain.Order;
import learnspringboot.core.dto.OrderDto;
import learnspringboot.core.service.CustomerService;
import learnspringboot.core.service.OrderService;
import learnspringboot.core.service.converter.OrderConverter;
import learnspringboot.core.service.converter.utils.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final OrderConverter converter;

    private final CustomerService customerService;

    @Autowired
    public OrderServiceImpl(final OrderRepository repository,
                            final OrderConverter converter,
                            final CustomerService customerService) {
        this.repository = repository;
        this.converter = converter;
        this.customerService = customerService;
    }

    public OrderDto findOne (final int id) {
        Optional<Order> order = repository.findById(id);

        return order
                .map(o -> converter.convertTo(View.NO_VIEW, o, customerId -> customerService.findOne(customerId)))
                .orElse(null);
    }
}
