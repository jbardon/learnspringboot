package learnspringboot.proxyshipper.service;

import learnspringboot.proxyshipper.dto.ShipmentDto;
import learnspringboot.proxyshipper.service.impl.ShipmentServiceImpl;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Test;

public class ShipmentServiceImplTest {

    private JUnitSoftAssertions softly;

    private ShipmentService service;

    @Before
    public void setUp() {
        service = new ShipmentServiceImpl();
        softly = new JUnitSoftAssertions();
    }

    @Test
    public void findOne() {
        ShipmentDto result = service.findOne(1);
        softly.assertThat(result).isNotNull();
    }
}