package learnspringboot.core.service.impl;

import learnspringboot.core.dao.OrderRepository;
import learnspringboot.core.dto.OrderDto;
import learnspringboot.core.service.CustomerService;
import learnspringboot.core.service.OrderService;
import learnspringboot.core.service.converter.OrderConverter;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class OrderServiceImplTest {

    private JUnitSoftAssertions softly;

    private OrderService service;

    @Before
    public void setUp() {
        service = new OrderServiceImpl(mock(OrderRepository.class), mock(OrderConverter.class), mock(CustomerService.class));
        softly = new JUnitSoftAssertions();
    }

    @Test
    public void findOne() {
        OrderDto result = service.findOne(1);
        softly.assertThat(result).isNotNull();
    }
}