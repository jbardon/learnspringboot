package learnspringboot.core.api;

import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.dto.criteria.ProductSearchCriteria;
import learnspringboot.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/product") // Route prefix for all endpoints in this controller

// ProductWs for WebService is also a suitable name
public class ProductApi {

    private final ProductService service;

    @Autowired
    public ProductApi(final ProductService service) {
        this.service = service;
    }

    // GET http://localhost:8080/product
    @RequestMapping(method = GET, path = "")
    public List<ProductDto> findAll () {
        return service.findAll();
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ProductDto findOne (
        @PathVariable final int id // Path variable because it comes from the url (in RequestMapping annotation)
    ) {
        return service.findOne(id);
    }

    @RequestMapping(method = GET, path = "/search")
    public List<ProductDto> search (
            @ModelAttribute final ProductSearchCriteria criteria
    ) {
        return service.search(criteria);
    }
}
