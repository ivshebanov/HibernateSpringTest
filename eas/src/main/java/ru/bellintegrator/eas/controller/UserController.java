package ru.bellintegrator.eas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.eas.model.User;
import ru.bellintegrator.eas.service.UserService;
import ru.bellintegrator.eas.view.UserView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list", method = {POST})
    public List<User> loadUser(Long officeId, UserView userView) {
        return userService.loadUser(officeId, userView);
    }

    @RequestMapping(value = "/id", method = {GET})
    public User loadById(Long id) {
        return userService.loadById(id);
    }

    @RequestMapping(value = "/update", method = {POST})
    public boolean update(UserView userView) {
        return userService.update(userView);
    }

    @RequestMapping(value = "/delete", method = {POST})
    public boolean delete(Long id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "/save", method = {POST})
    public boolean save(UserView userView) {
        return userService.save(userView);
    }
}
