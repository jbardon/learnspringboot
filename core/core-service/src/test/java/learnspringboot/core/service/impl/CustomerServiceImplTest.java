package learnspringboot.core.service.impl;

import learnspringboot.core.dao.CustomerRepository;
import learnspringboot.core.dto.CustomerDto;
import learnspringboot.core.service.CustomerService;
import learnspringboot.core.service.converter.CustomerConverter;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class CustomerServiceImplTest {

    private JUnitSoftAssertions softly;

    private CustomerService service;

    @Before
    public void setUp() {
        service = new CustomerServiceImpl(mock(CustomerRepository.class), mock(CustomerConverter.class));
        softly = new JUnitSoftAssertions();
    }

    @Test
    public void findOne() {
        CustomerDto result = service.findOne(1);
        softly.assertThat(result).isNotNull();
    }
}