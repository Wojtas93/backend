package pl.sdacademy.backend.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.sdacademy.backend.AuthorizedMockMvcRequestBuilder.authorizeGet;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    MockMvc mock;

    @Test
    void shouldReturn200() throws Exception {
        MvcResult mvcResult = mock
                .perform(authorizeGet("/user"))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void shouldReturn401() throws Exception {
        MvcResult mvcResult = mock
                .perform(get("/user"))
                .andReturn();
        assertEquals(401, mvcResult.getResponse().getStatus());
    }


}
