package learnspringboot.core.service;

import learnspringboot.core.dto.OrderDto;

public interface OrderService {
    OrderDto findOne(final int id);
}
