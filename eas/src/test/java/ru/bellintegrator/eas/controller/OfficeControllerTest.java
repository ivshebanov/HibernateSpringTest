package ru.bellintegrator.eas.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.bellintegrator.eas.service.OfficeService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/resources")
public class OfficeControllerTest {

    private OfficeService officeServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void loadOfficeTest() {

    }

    @Test
    public void loadByIdTest() {

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
