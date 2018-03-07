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
    public void test() {
        String login = "Ilya";
        String login2 = "Shebanov1";
        String password = "123h";
        String password2 = "12345";
        String name = "CPP";
        String hash = "qeqwe12sqwwedw32we3";

        Assert.assertTrue(registerDAO.register(login, password, name));

        Assert.assertTrue(registerDAO.login(login, password));
        Assert.assertFalse(registerDAO.login(login2, password2)); //неправильный логин

        Assert.assertTrue(registerDAO.activation(hash));
        Assert.assertTrue(registerDAO.activation("1212s1edwef3dwrf34d"));

    }
}
