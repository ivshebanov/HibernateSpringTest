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
import ru.bellintegrator.eas.dao.OfficeDAO;
import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.model.User;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeDAOTest {

    @Autowired
    private OfficeDAO officeDAO;

    @Test
    public void allTest() {
        List<Office> offices1 = officeDAO.all(1L);
        List<Office> offices2 = officeDAO.all(2L);

        Assert.assertTrue(offices1.size() == 1);
        Assert.assertTrue(offices2.size() == 1);

        Assert.assertTrue(offices1.get(0).getName().equals("belloffice"));
        Assert.assertTrue(offices2.get(0).getName().equals("Сбертехофис"));
    }

    @Test
    public void saveTest() {
        String name = "Перекресток офис";
        String address = "Малая Семеновская";
        int phone = 819231277;
        boolean isActive = true;
        Organization orgId = null;
        List<User> users = null;
        Office office = new Office();

        office.setName(name);
        office.setAddress(address);
        office.setPhone(phone);
        office.setActive(isActive);
        office.setOrgId(orgId);
        office.setUsers(users);
        Assert.assertTrue(officeDAO.save(office));

    }

    @Test
    public void loadTest() {
        Office office = officeDAO.load(1L);
        Assert.assertTrue(office != null);
        Assert.assertTrue(office.getName().equals("belloffice"));

        Office office2 = officeDAO.load(2L);
        Assert.assertTrue(office2 != null);
        Assert.assertTrue(office2.getName().equals("Сбертехофис"));
    }

    @Test
    public void deleteTest() {
        List<Office> offices = officeDAO.all(1L);
        Assert.assertTrue(offices.size() == 1);
        Assert.assertTrue(officeDAO.delete(1L));
        List<Office> postOffices = officeDAO.all(1L);
        Assert.assertTrue(postOffices.isEmpty());

    }

    @Test
    public void updateTest() {
        long id = 1L;
        String oldName = "belloffice";
        String name = "offbe";
        String address = "Большая Семеновская, 47";
        int oldPhone = 567898;
        int phone = 111112222;
        boolean isActive = true;
        Organization orgId = null;
        List<User> users = null;

        Assert.assertTrue(officeDAO.load(id).getName().equals(oldName));
        Assert.assertTrue(officeDAO.load(id).getPhone() == oldPhone);

        Office office = new Office();
        office.setId(id);
        office.setName(name);
        office.setAddress(address);
        office.setPhone(phone);
        office.setActive(isActive);
        office.setOrgId(orgId);
        office.setUsers(users);

        Assert.assertTrue(officeDAO.update(id, office));
        Assert.assertTrue(officeDAO.load(id).getName().equals(name));
        Assert.assertTrue(officeDAO.load(id).getPhone() == phone);
    }
}
