package learnspringboot.core.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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

    @Test
    public void findAll() throws Exception {
        ResultActions result = mockMvc.perform(get("/product"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$").value("findAll"));
    }

    @Test
    public void findOne() throws Exception {
        ResultActions result = mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$").value("findOne"));
    }

    @Test
    public void search() throws Exception {
        ResultActions result = mockMvc.perform(get("/product/search"))
                .andExpect(status().isOk());

        result.andExpect(jsonPath("$").value("search"));
    }

}