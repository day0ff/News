package com.day0ff.news.controller;

import com.day0ff.news.entity.Roles;
import com.day0ff.news.entity.Users;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("/")
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Users getUserById(@PathVariable("id") Long id) {
        return usersService.findById(id);
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    public List<Roles> getUserRolesById(@PathVariable("id") Long id) {
        Users user = usersService.findById(id);
        return user.getRoles();
    }
}
