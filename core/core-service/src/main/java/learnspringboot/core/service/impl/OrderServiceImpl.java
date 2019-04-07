package learnspringboot.core.service.impl;

import learnspringboot.core.dao.OrderRepository;
import learnspringboot.core.dao.ProductRepository;
import learnspringboot.core.domain.Order;
import learnspringboot.core.domain.Product;
import learnspringboot.core.dto.OrderDto;
import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.dto.criteria.ProductSearchCriteria;
import learnspringboot.core.service.OrderService;
import learnspringboot.core.service.ProductService;
import learnspringboot.core.service.converter.OrderConverter;
import learnspringboot.core.service.converter.ProductConverter;
import learnspringboot.core.service.converter.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final OrderConverter converter;

    @Autowired
    public OrderServiceImpl(final OrderRepository repository,
                            final OrderConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public OrderDto findOne (final int id) {
        Optional<Order> order = repository.findById(id);

        return order
                .map(o -> converter.convertTo(View.NO_VIEW, o))
                .orElse(null);
    }
}
