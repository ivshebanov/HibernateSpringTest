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
import ru.bellintegrator.eas.model.Office;

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
    public void loadOffice() {
        List<Office> offices = officeDAO.loadOffice(1L, "belloffice", 567898, true);
        Assert.assertTrue(offices.size() == 1);
    }

    @Test
    public void loadByIdTest() {
        Office office = officeDAO.loadById(1L);
        Assert.assertTrue(office.getName().equals("belloffice"));

        Office office2 = officeDAO.loadById(2L);
        Assert.assertTrue(office2.getName().equals("Сбертехофис"));
    }

    @Test
    public void updateTest() {
        long id = 1L;
        String name = "offbe";
        int phone = 111112222;

        Assert.assertTrue(officeDAO.loadById(id).getName().equals("belloffice"));
        Assert.assertTrue(officeDAO.loadById(id).getPhone() == 567898);

        Office office = new Office();
        office.setId(id);
        office.setName(name);
        office.setAddress("Большая Семеновская, 47");
        office.setPhone(phone);
        office.setActive(true);
        office.setOrgId(null);
        office.setUsers(null);

        Assert.assertTrue(officeDAO.update(id, office));
        Assert.assertTrue(officeDAO.loadById(id).getName().equals(name));
        Assert.assertTrue(officeDAO.loadById(id).getPhone() == phone);
    }

    @Test
    public void deleteTest() {
        List<Office> offices = officeDAO.all(1L);
        Assert.assertTrue(offices.size() == 1);
        Assert.assertTrue(officeDAO.delete(1L));
        List<Office> postOffices = officeDAO.all(1L);
        Assert.assertTrue(postOffices == null);
    }

    @Test
    public void saveTest() {
        Office office = new Office();
        office.setName("Перекресток офис");
        office.setAddress("Малая Семеновская");
        office.setPhone(819231277);
        office.setActive(true);
        office.setOrgId(null);
        Assert.assertTrue(officeDAO.save(office));
    }
}
