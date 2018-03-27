package ru.bellintegrator.eas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.eas.service.RegisterService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/register", method = {POST})
    public boolean register(String login, String password, String name) {
        return registerService.register(login, password, name);
    }

    @RequestMapping(value = "/login", method = {POST})
    public boolean login(String login, String password) {
        return registerService.login(login, password);
    }

    @RequestMapping(value = "/activation", method = {POST})
    public boolean activation(String hashCode) {
        return registerService.activation(hashCode);
    }
}
