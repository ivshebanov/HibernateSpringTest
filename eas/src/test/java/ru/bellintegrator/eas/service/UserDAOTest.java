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
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.UserDAO;
import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.model.Organization;
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
    public void allTest() throws MyException {
        List<User> users = userDAO.all(1L);
        Assert.assertTrue(users.size() == 1);
        List<User> users2 = userDAO.all(2L);
        Assert.assertTrue(users2.size() == 1);
    }

    @Test
    public void saveTest() throws MyException {
        String name = "Перекресток офис";
        String address = "Малая Семеновская";
        int phone1 = 819231277;
        boolean isActive = true;
        Organization orgId = null;
        List<User> users = null;

        Office office = new Office();
        office.setId(1L);
        office.setName(name);
        office.setAddress(address);
        office.setPhone(phone1);
        office.setActive(isActive);
        office.setOrgId(orgId);
        office.setUsers(users);

        Office officeId = office;
        String firstName = "Юзер";
        String secondName = "user";
        String middleName = "us";
        String position = "junior";
        int phone = 1283129;

        User user = new User();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setMiddleName(middleName);
        user.setPosition(position);
        user.setPhone(phone);
        user.setOfficeId(officeId);

        Assert.assertTrue(userDAO.save(user));
        List<User> users1 = userDAO.all(1L);
        Assert.assertTrue(userDAO.all(1L).size() == 2);

    }

    @Test
    public void loadTest() throws MyException {
        User user = userDAO.load(1L);
        Assert.assertTrue(user != null);
        Assert.assertTrue(user.getFirstName().equals("Сергей"));
        Assert.assertTrue(user.getMiddleName().equals("Викторович"));

        User user2 = userDAO.load(2L);
        Assert.assertTrue(user2 != null);
        Assert.assertTrue(user2.getFirstName().equals("Иван"));
        Assert.assertTrue(user2.getMiddleName().equals("Иванович"));
    }

    @Test
    public void deleteTest() throws MyException {
        List<User> users = userDAO.all(1L);
        Assert.assertTrue(users.size() == 1);

        Assert.assertTrue(userDAO.delete(1L));

        List<User> users1 = userDAO.all(1L);
        Assert.assertTrue(users1 == null);
    }

    @Test
    public void updateTest() throws MyException {
        String firstName = "Юзер";
        String secondName = "user";
        String middleName = "us";
        String position = "junior";
        int phone = 1283129;
        long id = 1L;

        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setMiddleName(middleName);
        user.setPosition(position);
        user.setPhone(phone);
        user.setOfficeId(null);

        Assert.assertTrue(userDAO.load(id).getFirstName().equals("Сергей"));
        Assert.assertTrue(userDAO.load(id).getMiddleName().equals("Викторович"));

        Assert.assertTrue(userDAO.update(id, user));

        Assert.assertTrue(userDAO.load(id).getFirstName().equals(firstName));
        Assert.assertTrue(userDAO.load(id).getMiddleName().equals(middleName));

    }
}
