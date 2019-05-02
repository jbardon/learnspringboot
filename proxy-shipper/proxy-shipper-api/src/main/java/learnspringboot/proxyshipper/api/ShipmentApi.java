package learnspringboot.proxyshipper.api;

import learnspringboot.proxyshipper.dto.ShipmentDto;
import learnspringboot.proxyshipper.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/shipment")
public class ShipmentApi {

    private final ShipmentService service;

    @Autowired
    public ShipmentApi(final ShipmentService service) {
        this.service = service;
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<ShipmentDto> findOne (
        @PathVariable final int id
    ) {
        return ResponseEntity.ok(service.findOne(id));
    }
}
