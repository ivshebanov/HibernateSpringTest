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
import ru.bellintegrator.eas.dao.OfficeDAO;
import ru.bellintegrator.eas.service.impl.OfficeServiceImpl;
import ru.bellintegrator.eas.view.OfficeView;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
@Transactional
public class OfficeServiceTest {

    @Autowired
    private OfficeDAO officeDAO;

    OfficeService officeService;

    @Before
    public void setUp() {
        this.officeService = new OfficeServiceImpl(officeDAO);
    }

    @Test
    public void loadOffice() {
        List<OfficeView> list = officeService.loadOffice(1L, "belloffice", 567898, true);
        Assert.assertEquals(Integer.parseInt(list.get(0).getId()), 1);
    }

    @Test
    public void loadByIdTest() {
        OfficeView offices = officeService.loadById(1L);
        Assert.assertEquals(Integer.parseInt(offices.getId()), 1);
    }

    @Test
    public void updateTest() {
        OfficeView officeView = new OfficeView();
        officeView.setId("1");
        officeView.setName("Ilya");
        officeView.setAddress("Semenovskaya");
        officeView.setPhone(374539);
        officeView.setActive(true);
        Assert.assertTrue(officeService.update(officeView));
    }

    @Test
    public void deleteTest() {
        Assert.assertTrue(officeService.delete(1L));
    }

    @Test
    public void saveTest() {
        OfficeView officeView = new OfficeView();
        officeView.setName("Ilya");
        officeView.setAddress("Semenovskaya");
        officeView.setPhone(374539);
        officeView.setActive(true);
        Assert.assertTrue(officeService.save(officeView));
    }
}
