package learnspringboot.core.api;

import learnspringboot.core.dto.CustomerDto;
import learnspringboot.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/customer")
public class CustomerApi {

    private final CustomerService service;

    @Autowired
    public CustomerApi(final CustomerService service) {
        this.service = service;
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<CustomerDto> findOne (
        @PathVariable final int id
    ) {
        return ResponseEntity.ok(service.findOne(id));
    }
}
