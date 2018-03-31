package ru.bellintegrator.eas.service.impl.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.view.OrganizationView;

public class OrganizationCustomMapper extends CustomMapper<Organization, OrganizationView> {

    @Override
    public void mapAtoB(Organization organization, OrganizationView organizationView, MappingContext context) {
        organizationView.setId(organization.getId().toString());
    }

    @Override
    public void mapBtoA(OrganizationView organizationView, Organization organization, MappingContext context) {
        organization.setId(Long.parseLong(organizationView.getId()));
    }
}
