package ru.bellintegrator.eas.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<User> loadUser(@RequestBody Long officeId, @RequestBody UserView userView) {
        return userService.loadUser(officeId, userView);
    }

    @RequestMapping(value = "/id", method = {GET})
    public User loadById(@RequestBody Long id) {
        return userService.loadById(id);
    }

    @RequestMapping(value = "/update", method = {POST})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public void update(@RequestBody UserView userView) {
        userService.update(userView);
    }

    @RequestMapping(value = "/delete", method = {POST})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public void delete(@RequestBody Long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/save", method = {POST})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public void save(@RequestBody UserView userView) {
        userService.save(userView);
    }
}
