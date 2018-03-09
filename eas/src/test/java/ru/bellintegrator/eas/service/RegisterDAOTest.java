package ru.bellintegrator.eas.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.Application;
import ru.bellintegrator.eas.dao.RegisterDAO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class RegisterDAOTest {
    @Autowired
    private RegisterDAO registerDAO;

    @Test
    public void registerTest() {
        String login = "Ilya";
        String password = "123h";
        String name = "CPP";
        Assert.assertTrue(registerDAO.register(login, password, name));
    }

    @Test
    public void loginTest() {
        String login = "Shebanov";
        String password = "12345";
        Assert.assertTrue(registerDAO.login(login, password));
        String login2 = "сбер";
        String password2 = "112233";
        Assert.assertTrue(registerDAO.login(login2, password2));
    }

    @Test
    public void activationTest() {
        Assert.assertTrue(registerDAO.activation("12345hashcode"));
        Assert.assertTrue(registerDAO.activation("112233hashcode"));
    }
}
