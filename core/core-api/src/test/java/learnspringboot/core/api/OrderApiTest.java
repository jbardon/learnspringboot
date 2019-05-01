package learnspringboot.core.api;

import learnspringboot.core.dto.OrderDto;
import learnspringboot.core.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static learnspringboot.core.domain.enums.OrderStatus.PENDING;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderApi.class)
public class OrderApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    @Test
    public void findOne() throws Exception {
        when(service.findOne(anyInt())).thenReturn(getOrderDto());

        ResultActions result = mockMvc.perform(get("/order/1"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$", notNullValue()));
        result.andExpect(jsonPath("$.id", is(1)));
        result.andExpect(jsonPath("$.status", is("PENDING")));
    }

    private OrderDto getOrderDto() {
        OrderDto dto = new OrderDto();
        dto.setId(1);
        dto.setStatus(PENDING.name());
        return dto;
    }
}