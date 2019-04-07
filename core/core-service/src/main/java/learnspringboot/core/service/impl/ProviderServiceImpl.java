package learnspringboot.core.service.impl;

import learnspringboot.core.dao.ProviderRepository;
import learnspringboot.core.domain.Provider;
import learnspringboot.core.dto.ProviderDto;
import learnspringboot.core.dto.criteria.ProductSearchCriteria;
import learnspringboot.core.service.ProductService;
import learnspringboot.core.service.ProviderService;
import learnspringboot.core.service.converter.ProviderConverter;
import learnspringboot.core.service.converter.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository repository;

    private final ProviderConverter converter;

    private final ProductService productService;

    @Autowired
    public ProviderServiceImpl(final ProviderRepository repository,
                               final ProviderConverter converter,
                               final ProductService productService) {
        this.repository = repository;
        this.converter = converter;
        this.productService = productService;
    }

    public ProviderDto findOne (final int id) {
        final Optional<Provider> provider = repository.findById(id);

        return provider
                .map(p -> converter.convertTo(View.NO_VIEW, p, productId -> {
                    ProductSearchCriteria criteria = new ProductSearchCriteria();
                    criteria.setProvider(Collections.singletonList(productId));
                    return productService.search(criteria);
                }))
                .orElse(null);
    }
}
