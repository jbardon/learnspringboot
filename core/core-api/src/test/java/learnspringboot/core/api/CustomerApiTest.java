package learnspringboot.core.api;

import learnspringboot.core.dto.CustomerDto;
import learnspringboot.core.service.CustomerService;
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
@WebMvcTest(CustomerApi.class)
public class CustomerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @Test
    public void findOne() throws Exception {
        when(service.findOne(anyInt())).thenReturn(getCustomerDto());

        ResultActions result = mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$", notNullValue()));
        result.andExpect(jsonPath("$.id", is(1)));
        result.andExpect(jsonPath("$.firstname", is("firstname")));
        result.andExpect(jsonPath("$.lastname", is("lastname")));
    }

    private CustomerDto getCustomerDto() {
        CustomerDto dto = new CustomerDto();
        dto.setId(1);
        dto.setFirstname("firstname");
        dto.setLastname("lastname");
        return dto;
    }
}