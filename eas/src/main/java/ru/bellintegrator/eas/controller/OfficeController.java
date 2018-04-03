package ru.bellintegrator.eas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.eas.service.OfficeService;
import ru.bellintegrator.eas.view.OfficeView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @RequestMapping(value = "/list", method = {POST})
    public List<OfficeView> loadOffice(@RequestBody @NotNull Long orgId, @RequestBody String name,
                                       @RequestBody int phone, @RequestBody boolean isActive) {
        return officeService.loadOffice(orgId, name, phone, isActive);
    }

    @RequestMapping(value = "/id", method = {GET})
    public OfficeView loadById(@RequestBody Long id) {
        return officeService.loadById(id);
    }

    @RequestMapping(value = "/update", method = {POST})
    public void update(@RequestBody @Valid OfficeView officeView) {
        officeService.update(officeView);
    }

    @RequestMapping(value = "/delete", method = {POST})
    public void delete(@RequestBody Long id) {
        officeService.delete(id);
    }

    @RequestMapping(value = "/save", method = {POST})
    public void save(@RequestBody @Valid OfficeView officeView) {
        officeService.save(officeView);
    }
}
