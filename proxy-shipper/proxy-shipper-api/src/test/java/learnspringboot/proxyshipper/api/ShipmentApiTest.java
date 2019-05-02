package learnspringboot.proxyshipper.api;

import learnspringboot.proxyshipper.dto.ShipmentDto;
import learnspringboot.proxyshipper.service.ShipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShipmentApi.class)
public class ShipmentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService service;

    @Test
    public void findOne() throws Exception {
        when(service.findOne(anyInt())).thenReturn(getShipmentDto());

        ResultActions result = mockMvc.perform(get("/shipment/1"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$", notNullValue()));
        result.andExpect(jsonPath("$.id", is(1)));
    }

    private ShipmentDto getShipmentDto() {
        ShipmentDto dto = new ShipmentDto();
        dto.setId(1);
        return dto;
    }
}