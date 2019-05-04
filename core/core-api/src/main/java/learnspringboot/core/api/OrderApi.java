package learnspringboot.core.api;

import learnspringboot.core.dto.OrderDto;
import learnspringboot.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private final OrderService service;

    @Autowired
    public OrderApi(final OrderService service) {
        this.service = service;
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<OrderDto> findOne (
        @PathVariable
        final int id,
        @RequestParam(value = "shipment", required = false)
        final boolean withShipment
    ) {
        final OrderDto order = withShipment
                ? service.getWithShipment(id)
                : service.findOne(id);

        if (order != null) {
            return ResponseEntity.ok(order);
        }

        return ResponseEntity.notFound().build();
    }
}
