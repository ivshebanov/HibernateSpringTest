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
import ru.bellintegrator.eas.dao.DocDAO;
import ru.bellintegrator.eas.model.Country;
import ru.bellintegrator.eas.model.Doc;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class DocDAOTest {

    @Autowired
    private DocDAO docDAO;

    @Test
    public void allTest() throws MyException {
        List<Doc> countries = docDAO.all();
        Assert.assertTrue(countries.size() == 11);
    }
}
