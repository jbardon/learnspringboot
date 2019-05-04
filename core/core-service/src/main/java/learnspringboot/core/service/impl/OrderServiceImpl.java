package learnspringboot.core.service.impl;

import learnspringboot.core.dao.OrderRepository;
import learnspringboot.core.domain.Order;
import learnspringboot.core.dto.OrderDto;
import learnspringboot.core.service.CustomerService;
import learnspringboot.core.service.OrderService;
import learnspringboot.core.service.client.ProxyShipperClient;
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

    private final ProxyShipperClient proxyShipperClient;

    @Autowired
    public OrderServiceImpl(final OrderRepository repository,
                            final OrderConverter converter,
                            final CustomerService customerService,
                            final ProxyShipperClient proxyShipperClient) {
        this.repository = repository;
        this.converter = converter;
        this.customerService = customerService;
        this.proxyShipperClient = proxyShipperClient;
    }

    public OrderDto findOne (final int id) {
        Optional<Order> order = repository.findById(id);

        return order
                .map(o -> converter.convertTo(View.NO_VIEW, o, customerService::findOne))
                .orElse(null);
    }

    @Override
    public OrderDto getWithShipment(int id) {
        final OrderDto order = findOne(id);
        if (order != null) {
            order.setShipment(proxyShipperClient.getShipment(id));
        }
        return order;
    }
}
