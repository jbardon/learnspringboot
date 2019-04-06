package learnspringboot.core.service.impl;

import learnspringboot.core.dao.ProductRepository;
import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.dto.criteria.ProductSearchCriteria;
import learnspringboot.core.service.ProductService;
import learnspringboot.core.service.converter.ProductConverter;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.mock;

public class ProductServiceImplTest {

    // @Rule ??
    private JUnitSoftAssertions softly;

    private ProductService service;

    // No need to mock in tests
    //@Mock
    //private ProductRepository repository;

    @Before
    public void setUp() {
        service = new ProductServiceImpl(mock(ProductRepository.class), mock(ProductConverter.class));
        softly = new JUnitSoftAssertions();
    }

    @Test
    public void findAll() {
        List<ProductDto> result = service.findAll();

        softly.assertThat(result).isNotNull();
        softly.assertThat(result).isNotEmpty();
    }

    @Test
    public void findOne() {
        ProductDto result = service.findOne(1);

        softly.assertThat(result).isNotNull();
    }

    @Test
    public void search() {
        ProductSearchCriteria criteria = new ProductSearchCriteria();
        List<ProductDto> result = service.search(criteria);

        softly.assertThat(result).isNotNull();
        softly.assertThat(result).isNotEmpty();
    }
}