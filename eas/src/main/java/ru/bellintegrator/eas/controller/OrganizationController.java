package ru.bellintegrator.eas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.service.OrganizationService;
import ru.bellintegrator.eas.view.OrganizationView;

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
    public List<Organization> loadOrganization(String name, OrganizationView organizationView) {
        return organizationService.loadOrganization(name, organizationView);
    }

    @RequestMapping(value = "/id", method = {GET})
    public Organization loadById(Long id) {
        return organizationService.loadById(id);
    }

    @RequestMapping(value = "/update", method = {POST})
    public boolean update(OrganizationView organizationView) {
        return organizationService.update(organizationView);
    }

    @RequestMapping(value = "/delete", method = {POST})
    public boolean delete(Long id) {
        return organizationService.delete(id);
    }

    @RequestMapping(value = "/save", method = {POST})
    public boolean save(OrganizationView organizationView) {
        return organizationService.save(organizationView);
    }
}
