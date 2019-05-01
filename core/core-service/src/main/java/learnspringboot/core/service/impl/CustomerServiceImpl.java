package learnspringboot.core.service.impl;

import learnspringboot.core.dao.CustomerRepository;
import learnspringboot.core.domain.Customer;
import learnspringboot.core.dto.CustomerDto;
import learnspringboot.core.service.CustomerService;
import learnspringboot.core.service.converter.CustomerConverter;
import learnspringboot.core.service.converter.utils.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    private final CustomerConverter converter;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository repository,
                               final CustomerConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public CustomerDto findOne (final int id) {
        Optional<Customer> order = repository.findById(id);

        return order
                .map(o -> converter.convertTo(View.NO_VIEW, o))
                .orElse(null);
    }
}
