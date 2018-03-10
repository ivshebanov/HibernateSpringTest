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
import ru.bellintegrator.eas.dao.UserDAO;
import ru.bellintegrator.eas.model.User;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void allTest() {
        List<User> users = userDAO.all(1L);
        System.out.println(users.size());

        Assert.assertTrue(users.size() == 1);

        List<User> users2 = userDAO.all(2L);
        Assert.assertTrue(users2.size() == 1);
    }

    @Test
    public void saveTest() {

    }

    @Test
    public void loadTest() {

    }

    @Test
    public void deleteTest() {

    }

    @Test
    public void updateTest() {

    }
}
