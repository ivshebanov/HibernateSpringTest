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
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.service.impl.OrganizationServiceImpl;
import ru.bellintegrator.eas.view.OrganizationView;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
@Transactional
public class OrganizationServiceTest {

    @Autowired
    private OrganizationDAO organizationDAO;

    OrganizationService organizationService;

    @Before
    public void setUp() {
        this.organizationService = new OrganizationServiceImpl(organizationDAO);
    }

    @Test
    public void loadOrganizationTest() {
        List<OrganizationView> list = organizationService.loadOrganization("bell", 1234567890, true);
        Assert.assertEquals(Integer.parseInt(list.get(0).getId()), 1);
    }

    @Test
    public void loadByIdTest() {
        OrganizationView organizationView = organizationService.loadById(1L);
        Assert.assertEquals(Integer.parseInt(organizationView.getId()), 1);
    }

    @Test
    public void updateTest() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId("1");
        organizationView.setName("Ilya");
        organizationView.setFullName("Shebanov");
        organizationView.setInn(555666);
        organizationView.setKpp(777888);
        organizationView.setAddress("Semenovskaya");
        organizationView.setPhone(374539);
        organizationView.setActive(true);
        Assert.assertTrue(organizationService.update(organizationView));
    }

    @Test
    public void deleteTest() {
        Assert.assertTrue(organizationService.delete(1L));
    }

    @Test
    public void saveTest() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Ilya");
        organizationView.setFullName("Shebanov");
        organizationView.setInn(555666);
        organizationView.setKpp(777888);
        organizationView.setAddress("Semenovskaya");
        organizationView.setPhone(374539);
        organizationView.setActive(true);
        Assert.assertTrue(organizationService.save(organizationView));
    }
}
