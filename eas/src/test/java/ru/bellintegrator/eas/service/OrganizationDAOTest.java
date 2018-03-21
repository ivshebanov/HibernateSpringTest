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
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.model.Organization;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationDAOTest {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Test
    public void allTest() throws MyException {
        Assert.assertTrue(organizationDAO.all().size() == 2);
    }

    @Test
    public void saveTest() throws MyException {
        String name = "Перекресток";
        String fullName = "OAO Прекресток";
        int inn = 1273749495;
        int kpp = 1848853923;
        String address = "Большая Семеновская, 47";
        int phone = 819221312;
        boolean isActive = false;
        List<Office> office = null;

        Assert.assertTrue(organizationDAO.all().size() == 2);

        Organization organization = new Organization();
        organization.setName(name);
        organization.setFullName(fullName);
        organization.setInn(inn);
        organization.setKpp(kpp);
        organization.setAddress(address);
        organization.setPhone(phone);
        organization.setActive(isActive);
        organization.setOffices(office);

        Assert.assertTrue(organizationDAO.save(organization));
        Assert.assertTrue(organizationDAO.all().size() == 3);
    }

    @Test
    public void loadTest() throws MyException {
        Organization organization = organizationDAO.load(1L);
        Assert.assertTrue(organization != null);
        Assert.assertTrue(organization.getName().equals("bell"));

        Organization organization2 = organizationDAO.load(2L);
        Assert.assertFalse(organization2 != null);
    }

    @Test
    public void deleteTest() throws MyException {
        List<Organization> listOrganization = organizationDAO.all();
        Assert.assertTrue(listOrganization.size() == 2);

        Assert.assertTrue(organizationDAO.delete(1L));

        List<Organization> listOrganization1 = organizationDAO.all();
        Assert.assertTrue(listOrganization1.size() == 1);
        Assert.assertTrue(listOrganization1.get(0).getName().equals("Сбертех"));
    }

    @Test
    public void updateTest() throws MyException {
        long id = 1L;
        String name = "bell";
        String fullName = "OAO bell";
        int inn = 1273749495;
        int oldInn = 1234567890;
        int kpp = 1848853923;
        String address = "Большая Семеновская, 47";
        int phone = 819221312;
        boolean isActive = true;
        List<Office> office = null;

        Assert.assertTrue(organizationDAO.load(id).getFullName().equals("bellintegrator"));
        Assert.assertTrue(organizationDAO.load(id).getInn() == oldInn);

        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        organization.setFullName(fullName);
        organization.setInn(inn);
        organization.setKpp(kpp);
        organization.setAddress(address);
        organization.setPhone(phone);
        organization.setActive(isActive);
        organization.setOffices(office);

        Assert.assertTrue(organizationDAO.update(id, organization));

        Assert.assertTrue(organizationDAO.load(id).getFullName().equals("OAO bell"));
        Assert.assertTrue(organizationDAO.load(id).getInn() == inn);
    }

    @Test
    public void registerTest() throws MyException {
        String login = "Ilya";
        String password = "123h";
        String name = "CPP";
        Assert.assertTrue(organizationDAO.register(login, password, name));
    }

    @Test
    public void loginTest() throws MyException {
        String login = "Shebanov";
        String password = "12345";
        Assert.assertTrue(organizationDAO.login(login, password));
        String login2 = "сбер";
        String password2 = "112233";
        Assert.assertTrue(organizationDAO.login(login2, password2));
    }

    @Test
    public void activationTest() throws MyException {
        Assert.assertTrue(organizationDAO.activation("12345hashcode"));
        Assert.assertTrue(organizationDAO.activation("112233hashcode"));
    }
}

