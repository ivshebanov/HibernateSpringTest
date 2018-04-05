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
import ru.bellintegrator.eas.dao.UserDAO;
import ru.bellintegrator.eas.service.impl.UserServiceImpl;
import ru.bellintegrator.eas.view.UserView;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
@Transactional
public class UserServiceTest {

    @Autowired
    private UserDAO userDAO;

    UserService userService;

    @Before
    public void setUp() {
        this.userService = new UserServiceImpl(userDAO);
    }

    @Test
    public void loadUserTest() {
        List<UserView> list = userService.loadUser(1L, "Сергей", "Сидоров",
                "Викторович", "Руководитель отдела", 5, 1);
        Assert.assertEquals(Integer.parseInt(list.get(0).getId()), 1);
    }

    @Test
    public void loadByIdTest() {
        UserView userView = userService.loadById(1L);
        Assert.assertEquals(Integer.parseInt(userView.getId()), 1);
    }

    @Test
    public void updateTest() {
        UserView userView = new UserView();
        userView.setId("1");
        userView.setFirstName("Юзер");
        userView.setSecondName("user");
        userView.setMiddleName("us");
        userView.setPosition("junior");
        userView.setPhone(1283129);
        userView.setDocCode(6);
        userView.setDocName("Удостоверение беженца");
        userView.setDocNumber(62);
        userView.setDocDate(new Date());
        userView.setCitizenshipName("Ватикан");
        userView.setCitizenshipCode(8);
        userView.setIdentified(true);
        Assert.assertTrue(userService.update(userView));
    }

    @Test
    public void deleteTest() {
        Assert.assertTrue(userService.delete(1L));
    }

    @Test
    public void saveTest() {
        UserView userView = new UserView();
        userView.setFirstName("Юзер");
        userView.setSecondName("user");
        userView.setMiddleName("us");
        userView.setPosition("junior");
        userView.setPhone(1283129);
        userView.setDocCode(6);
        userView.setDocName("Удостоверение беженца");
        userView.setDocNumber(62);
        userView.setDocDate(new Date());
        userView.setCitizenshipName("Ватикан");
        userView.setCitizenshipCode(8);
        userView.setIdentified(true);
        Assert.assertTrue(userService.save(userView));
    }
}
