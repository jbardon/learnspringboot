package learnspringboot.core.api;

import learnspringboot.core.dto.ProductDto;
import learnspringboot.core.dto.criteria.ProductSearchCriteria;
import learnspringboot.core.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductApi.class) // Instantiate MockMvc and include the minimal configuration
/*
    Avoid these annotation which load the full spring context:
    - @SpringBootTest
    - @AutoConfigureMockMvc
 */
public class ProductApiTest {

    @Autowired
    private MockMvc mockMvc;

    // Short for @Mock and bean used for @Autowired
    // @Mock does: service = mock(ProductService.class)
    @MockBean
    private ProductService service;

    @Test
    public void findAll() throws Exception
    {
        when(service.findAll()).thenReturn(Collections.singletonList(getProductDto()));

        ResultActions result = mockMvc.perform(get("/product"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$").isArray());
        result.andExpect(jsonPath("$", hasSize(1)));
        result.andExpect(jsonPath("$[0].id", is(1)));
        result.andExpect(jsonPath("$[0].name", is("name")));
        result.andExpect(jsonPath("$[0].make", is("make")));
        result.andExpect(jsonPath("$[0].price", is(1.2)));
    }

    @Test
    public void findOne() throws Exception
    {
        when(service.findOne(anyInt())).thenReturn(getProductDto());

        ResultActions result = mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$", notNullValue()));
        result.andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void search() throws Exception
    {
        when(service.search(any(ProductSearchCriteria.class))).thenReturn(Collections.singletonList(getProductDto()));

        ResultActions result = mockMvc.perform(get("/product/search"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$").isArray());
        result.andExpect(jsonPath("$", hasSize(1)));
        result.andExpect(jsonPath("$[0].id", is(1)));
    }

    private ProductDto getProductDto() {
        ProductDto dto = new ProductDto();
        dto.setId(1);
        dto.setName("name");
        dto.setMake("make");
        dto.setPrice(1.2);
        return dto;
    }

}