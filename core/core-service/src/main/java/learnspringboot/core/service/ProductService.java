package learnspringboot.core.service;

import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.dto.criteria.ProductSearchCriteria;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll ();

    ProductDto findOne (final int id);

    List<ProductDto> search (final ProductSearchCriteria criteria);

}
