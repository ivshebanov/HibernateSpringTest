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
import ru.bellintegrator.eas.model.Organization;

import java.security.NoSuchAlgorithmException;
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
    public void loadOrganization() throws MyException {
        List<Organization> organizations = organizationDAO.loadOrganization("bell", 1234567890, true);
    }

    @Test
    public void loadByIdTest() throws MyException {
        Organization organization = organizationDAO.loadById(1L);
        Assert.assertTrue(organization != null);
        Assert.assertTrue(organization.getName().equals("bell"));
    }

    @Test
    public void updateTest() throws MyException {
        Long id = 1L;
        int oldInn = 1234567890;
        int inn = 223749495;

        Assert.assertTrue(organizationDAO.loadById(id).getFullName().equals("bellintegrator"));
        Assert.assertTrue(organizationDAO.loadById(id).getInn() == oldInn);

        Organization organization = new Organization();
        organization.setId(id);
        organization.setName("bell");
        organization.setFullName("OAO bell");
        organization.setInn(inn);
        organization.setKpp(1848853923);
        organization.setAddress("Большая Семеновская, 47");
        organization.setPhone(819221312);
        organization.setActive(true);
        organization.setOffices(null);

        Assert.assertTrue(organizationDAO.update(organization.getId(), organization));

        Assert.assertTrue(organizationDAO.loadById(id).getFullName().equals("OAO bell"));
        Assert.assertTrue(organizationDAO.loadById(id).getInn() == inn);
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
    public void saveTest() throws MyException {
        Assert.assertTrue(organizationDAO.all().size() == 2);

        Organization organization = new Organization();
        organization.setName("Перекресток");
        organization.setFullName("OAO Прекресток");
        organization.setLogin("Перекресток");
        organization.setPassword("wed23he23dhj98dh273h23dh283d2d32");
        organization.setInn(1273749495);
        organization.setKpp(1848853923);
        organization.setAddress("Большая Семеновская, 47");
        organization.setPhone(819221312);
        organization.setActive(false);
        organization.setHashActive("hrtyeduhj287dh293d8j29038edyuq9dfhj34");
        organization.setOffices(null);

        Assert.assertTrue(organizationDAO.save(organization));
        Assert.assertTrue(organizationDAO.all().size() == 3);
    }

    @Test
    public void registerTest() throws MyException, NoSuchAlgorithmException {
        Assert.assertTrue(organizationDAO.register("Ilya", "123h", "CPP"));
        Assert.assertTrue(organizationDAO.login("Ilya", "123h"));
    }

    @Test
    public void loginTest() throws MyException, NoSuchAlgorithmException {
        Assert.assertTrue(organizationDAO.login("Shebanov", "12345"));
        Assert.assertTrue(organizationDAO.login("сбер", "112233"));
    }

    @Test
    public void activationTest() throws MyException {
        Assert.assertTrue(organizationDAO.activation("112233hashcode"));
    }
}

