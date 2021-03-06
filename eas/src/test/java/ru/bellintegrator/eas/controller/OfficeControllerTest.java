package ru.bellintegrator.eas.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.bellintegrator.eas.Application;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
@Transactional
public class OfficeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void loadOfficeTest() {

    }

    @Test
    public void loadByIdTest() throws Exception {
        mockMvc.perform(get("/api/office/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("belloffice"))).andDo(print()).andReturn();
    }

    @Test
    public void updateTest() {

    }

    @Test
    public void deleteTest() {

    }

    @Test
    public void saveTest() {

    }
}
