package ru.bellintegrator.eas.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.bellintegrator.eas.service.RegisterService;

@RunWith(SpringRunner.class)
public class RegisterControllerTest {

    private RegisterService registerService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void registerTest() {

    }

    @Test
    public void loginTest() {

    }

    @Test
    public void activationTest() {

    }
}
