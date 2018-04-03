package ru.bellintegrator.eas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.eas.service.OrganizationService;
import ru.bellintegrator.eas.view.OrganizationView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/list", method = {POST})
    public List<OrganizationView> loadOrganization(@RequestBody @NotNull String name, @RequestBody int inn,
                                                   @RequestBody boolean isActive) {
        return organizationService.loadOrganization(name, inn, isActive);
    }

    @RequestMapping(value = "/id", method = {GET})
    public OrganizationView loadById(@RequestBody Long id) {
        return organizationService.loadById(id);
    }

    @RequestMapping(value = "/update", method = {POST})
    public void update(@RequestBody @Valid OrganizationView organizationView) {
        organizationService.update(organizationView);
    }

    @RequestMapping(value = "/delete", method = {POST})
    public void delete(@RequestBody Long id) {
        organizationService.delete(id);
    }

    @RequestMapping(value = "/save", method = {POST})
    public void save(@RequestBody @Valid OrganizationView organizationView) {
        organizationService.save(organizationView);
    }
}
