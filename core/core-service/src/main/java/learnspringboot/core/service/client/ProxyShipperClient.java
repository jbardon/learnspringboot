package learnspringboot.core.service.client;

import learnspringboot.proxyshipper.dto.ShipmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

// Name of micro-service registered in Eureka
// Check eureka registry or micro-service properties (spring.application.name)
// @FeignClient("proxy-shipper")

// When giving url, no call to Eureka for service name resolution
// Nor client-side load balancing with Ribbon
@FeignClient(name = "proxy-shipper", url ="http://localhost:8002/shipment")
public interface ProxyShipperClient {

    // Same annotations as SpringBoot RestController (thanks to Feign integration)
    @RequestMapping(method = GET, path = "/{id}")
    ShipmentDto getShipment (
        @PathVariable("id") final int id
    );
}
