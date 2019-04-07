package learnspringboot.core.api;

import learnspringboot.core.dto.ProviderDto;
import learnspringboot.core.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/Provider")
public class ProviderApi {

    private final ProviderService service;

    @Autowired
    public ProviderApi(final ProviderService service) {
        this.service = service;
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ProviderDto findOne (
        @PathVariable final int id
    ) {
        return service.findOne(id);
    }
}
