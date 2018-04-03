package ru.bellintegrator.eas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.eas.service.RegisterService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/")
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/all", method = {GET})
    public String all() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @RequestMapping(value = "/register", method = {POST})
    public void register(@RequestBody String login, @RequestBody String password, @RequestBody String name) {
        registerService.register(login, password, name);
    }

    @RequestMapping(value = "/login", method = {POST})
    public void login(@RequestBody String login, @RequestBody String password) {
        registerService.login(login, password);
    }

    @RequestMapping(value = "/activation", method = {POST})
    public void activation(@RequestBody String hashCode) {
        registerService.activation(hashCode);
    }
}
