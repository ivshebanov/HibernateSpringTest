package ru.bellintegrator.eas.dao;

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
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.User;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OfficeDAO officeDAO;

    @Test
    public void allTest() {
        List<User> users = userDAO.all(1L);
        Assert.assertTrue(users.size() == 1);
        List<User> users2 = userDAO.all(2L);
        Assert.assertTrue(users2.size() == 1);
    }

    @Test
    public void loadUser() {

    }

    @Test
    public void loadByIdTest() {
        User user = userDAO.loadById(1L);
        Assert.assertTrue(user != null);
        Assert.assertTrue(user.getFirstName().equals("Сергей"));
        Assert.assertTrue(user.getMiddleName().equals("Викторович"));

        User user2 = userDAO.loadById(2L);
        Assert.assertTrue(user2 != null);
        Assert.assertTrue(user2.getFirstName().equals("Иван"));
        Assert.assertTrue(user2.getMiddleName().equals("Иванович"));
    }

    @Test
    public void updateTest() throws MyException {
        long id = 1L;

        User user = new User();
        user.setId(id);
        user.setFirstName("Юзер");
        user.setSecondName("user");
        user.setMiddleName("us");
        user.setPosition("junior");
        user.setPhone(1283129);

        Assert.assertTrue(userDAO.loadById(id).getFirstName().equals("Сергей"));
        Assert.assertTrue(userDAO.loadById(id).getMiddleName().equals("Викторович"));

        Assert.assertTrue(userDAO.update(id, user, 4, "Паспорт иностранного гражданина",
                6, "Болгария"));

        Assert.assertTrue(userDAO.loadById(id).getFirstName().equals("Юзер"));
        Assert.assertTrue(userDAO.loadById(id).getMiddleName().equals("us"));
    }

    @Test
    public void deleteTest() {
        List<User> users = userDAO.all(1L);
        Assert.assertTrue(users.size() == 1);

        Assert.assertTrue(userDAO.delete(1L));

        List<User> users1 = userDAO.all(1L);
        Assert.assertTrue(users1 == null);
    }

    @Test
    public void saveTest() throws MyException {
        User user = new User();
        user.setFirstName("Юзер");
        user.setSecondName("user");
        user.setMiddleName("us");
        user.setPosition("junior");
        user.setPhone(1283129);
        user.setIdentified(true);
        user.setDocNumber(232);
        user.setDocDate(new Date());

        Assert.assertTrue(userDAO.save(user, 4, "Паспорт иностранного гражданина",
                6, "Болгария"));
    }
}
