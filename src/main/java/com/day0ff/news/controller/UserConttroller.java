package com.day0ff.news.controller;


import com.day0ff.news.entity.Persons;
import com.day0ff.news.entity.Roles;
import com.day0ff.news.entity.Users;
import com.day0ff.news.service.PersonsService;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserConttroller {
    @Autowired
    private UsersService usersService;

    @Autowired
    private PersonsService personsService;

    @RequestMapping(value = "/person/name", method = RequestMethod.POST)
    public Persons getPersonByName(@RequestParam("name") String name) {
        return personsService.findByName(name);
    }

    @RequestMapping(value = "/person/id", method = RequestMethod.POST)
    public Persons getPersonParamsById(@RequestParam("id") Long id) {
        return personsService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Users getUserByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        return usersService.findByNameAndPassword(name, password);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public List<String> getPersonRolesByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        List<Roles> roles = usersService.findByNameAndPassword(name, password).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Persons getPersonByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        return personsService.findByNameAndPassword(name, password);
    }
}
