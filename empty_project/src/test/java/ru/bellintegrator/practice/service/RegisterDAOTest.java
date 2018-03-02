package ru.bellintegrator.practice.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dao.RegisterDAO;

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
        String password = "12345";
        String name = "СПП";

        Assert.assertTrue(registerDAO.register(login, password, name));
        Assert.assertTrue(registerDAO.login(login, password));
    }
}
