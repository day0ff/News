package com.day0ff.news.controller;

import com.day0ff.news.entity.UserRoles;
import com.day0ff.news.entity.Users;
import com.day0ff.news.service.UserRolesService;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRolesService userRolesService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<UserRoles> getAllUsersRoles() {
        return userRolesService.findAll();
    }

    @RequestMapping(value = "/roles/{name}", method = RequestMethod.GET)
    public List<UserRoles> getUserRoles(@PathVariable("name") String name) {
        return userRolesService.findByUserName(name);
    }
}
