package learnspringboot.core.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/product") // Route prefix for all endpoints in this controller

// ProductWs for WebService is also a suitable name
public class ProductApi {

    // GET http://localhost:8080/product
    @RequestMapping(method = GET, path = "")
    public String findAll () {
        return "findAll";
    }

    @RequestMapping(method = GET, path = "/{id}")
    public String findOne (
        @PathVariable final int id // Path variable because it comes from the url (in RequestMapping annotation)
    ) {
        return "findOne";
    }

    @RequestMapping(method = GET, path = "/search")
    public String search () {
        return "search";
    }

}
