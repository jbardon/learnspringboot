package learnspringboot.proxyshipper.service;

import learnspringboot.proxyshipper.dto.ShipmentDto;

public interface ShipmentService {
    ShipmentDto findOne(final int id);
}
