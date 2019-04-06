package learnspringboot.core.service.impl;

import learnspringboot.core.dao.ProductRepository;
import learnspringboot.core.domain.Product;
import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.dto.criteria.ProductSearchCriteria;
import learnspringboot.core.service.ProductService;
import learnspringboot.core.service.converter.ProductConverter;
import learnspringboot.core.service.converter.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    This is a stereotype used to add this class in the IOC containers (spring context)
    Other stereotypes are: Component, Repository, RestController and Controller
    Some of them provides more than only registration in spring context (ex: RestController and Repository)

    You can define scope for beans from classes with @Scope
    Here are the scope levels: singleton (defaut), prototype (each call), request, session, global session, application, websocket
*/
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final ProductConverter converter;

    @Autowired
    public ProductServiceImpl(final ProductRepository repository,
                              final ProductConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<ProductDto> findAll () {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(p -> converter.convertTo(View.NO_VIEW, p))
                .collect(Collectors.toList());
    }

    public ProductDto findOne (final int id) {
        Optional<Product> product = repository.findById(id);

        return product
                .map(product1 -> converter.convertTo(View.NO_VIEW, product1))
                .orElse(null);
    }

    public List<ProductDto> search (final ProductSearchCriteria criteria) {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(p -> converter.convertTo(View.NO_VIEW, p))
                .collect(Collectors.toList());
    }
}
