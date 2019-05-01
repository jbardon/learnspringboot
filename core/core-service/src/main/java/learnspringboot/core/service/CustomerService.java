package learnspringboot.core.service;

import learnspringboot.core.dto.CustomerDto;

public interface CustomerService {
    CustomerDto findOne(final int id);
}
