package com.day0ff.news.controller;

import com.day0ff.news.entity.Users;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Users> getAllPassports() {
        return usersService.findAll();
    }
}
