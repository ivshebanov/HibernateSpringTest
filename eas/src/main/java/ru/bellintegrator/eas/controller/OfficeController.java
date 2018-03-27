package ru.bellintegrator.eas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.service.OfficeService;
import ru.bellintegrator.eas.view.OfficeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @RequestMapping(value = "/list", method = {POST})
    public List<Office> loadOffice(Long orgId, OfficeView officeView) {
        return officeService.loadOffice(orgId, officeView);
    }

    @RequestMapping(value = "/id", method = {GET})
    public Office loadById(Long id) {
        return officeService.loadById(id);
    }

    @RequestMapping(value = "/update", method = {POST})
    public boolean update(OfficeView officeView) {
        return officeService.update(officeView);
    }

    @RequestMapping(value = "/delete", method = {POST})
    public boolean delete(Long id) {
        return officeService.delete(id);
    }

    @RequestMapping(value = "/save", method = {POST})
    public boolean save(OfficeView officeView) {
        return officeService.save(officeView);
    }
}
