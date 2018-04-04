package ru.bellintegrator.eas.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.Application;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.service.impl.RegisterServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
@Transactional
public class RegisterServiceTest {

    @Autowired
    private OrganizationDAO organizationDAO;

    RegisterService registerService;

    @Before
    public void setUp() {
        this.registerService = new RegisterServiceImpl(organizationDAO);
    }

    @Test
    public void registerTest() {
        Assert.assertTrue(registerService.register("Ivannn@gmail.com", "y85ire8", "Ivan"));
    }

    @Test
    public void loginTest() {
        Assert.assertTrue(registerService.login("Shebanov", "12345"));
    }

    @Test
    public void activationTest() {
        Assert.assertTrue(registerService.activation("112233hashcode"));
    }
}
