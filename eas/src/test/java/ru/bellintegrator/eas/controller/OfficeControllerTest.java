package ru.bellintegrator.eas.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeControllerTest {

    @Autowired
    private OfficeController controller;

    @Test
    public void loadOfficeTest() {

    }

    @Test
    public void loadByIdTest() {

    }

    @Test
    public void updateTest()  {

    }

    @Test
    public void deleteTest() {

    }

    @Test
    public void saveTest() {

    }
}
