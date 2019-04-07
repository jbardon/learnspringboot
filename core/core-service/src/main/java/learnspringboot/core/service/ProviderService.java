package learnspringboot.core.service;

import learnspringboot.core.dto.ProviderDto;

public interface ProviderService {
    ProviderDto findOne(final int id);
}
