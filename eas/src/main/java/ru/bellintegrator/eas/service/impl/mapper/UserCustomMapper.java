package ru.bellintegrator.eas.service.impl.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ru.bellintegrator.eas.model.User;
import ru.bellintegrator.eas.view.UserView;

public class UserCustomMapper extends CustomMapper<User, UserView> {

    @Override
    public void mapAtoB(User user, UserView userView, MappingContext context) {
        userView.setId(user.getId().toString());
    }

    @Override
    public void mapBtoA(UserView userView, User user, MappingContext context) {
        user.setId(Long.parseLong(userView.getId()));
    }
}
