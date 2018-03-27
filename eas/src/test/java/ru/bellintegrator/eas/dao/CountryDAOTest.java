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
import ru.bellintegrator.eas.model.Country;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class CountryDAOTest {

    @Autowired
    private CountryDAO countryDAO;

    @Test
    public void allTest() throws MyException {
        List<Country> countries = countryDAO.all();
        Assert.assertTrue(countries.size() == 14);
    }
}
