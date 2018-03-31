package ru.bellintegrator.eas.service.impl.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.view.OfficeView;

public class OfficeCustomMapper extends CustomMapper<Office, OfficeView> {

    @Override
    public void mapAtoB(Office office, OfficeView officeView, MappingContext context) {
        officeView.setId(office.getId().toString());
    }

    @Override
    public void mapBtoA(OfficeView officeView, Office office, MappingContext context) {
        office.setId(Long.parseLong(officeView.getId()));
    }
}
