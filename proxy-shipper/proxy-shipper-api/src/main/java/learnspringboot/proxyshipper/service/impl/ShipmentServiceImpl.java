package learnspringboot.proxyshipper.service.impl;

import learnspringboot.proxyshipper.dto.ShipmentDto;
import learnspringboot.proxyshipper.service.ShipmentService;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    public ShipmentDto findOne (final int id) {
        ShipmentDto dto = new ShipmentDto();
        dto.setId(id);
        return dto;
    }
}
