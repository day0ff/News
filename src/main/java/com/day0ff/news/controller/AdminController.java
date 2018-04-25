package com.day0ff.news.controller;

import com.day0ff.news.entity.Persons;
import com.day0ff.news.entity.Roles;
import com.day0ff.news.service.PersonsService;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private PersonsService personsService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Persons> getPerson() {
        return personsService.findAll();
    }

/*    @RequestMapping(value = "/person/id", method = RequestMethod.POST)
    public Persons getPersonParamsById(@RequestParam("id") Long id) {
        return personsService.findById(id);
    }*/

    @RequestMapping(value = "/person/get/{id}", method = RequestMethod.GET)
    public Persons getPersonById(@PathVariable("id") Long id) {
        return personsService.findById(id);
    }

/*    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public List<String> getPersonRolesByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        List<Roles> roles = usersService.findByNameAndPassword(name, password).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }*/

/*    @RequestMapping(value = "/person/roles/{id}", method = RequestMethod.GET)
    public List<String> getPersonRolesByNameAndPassword(@PathVariable("id") Long id) {
        List<Roles> roles = usersService.findById(id).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }*/

    @RequestMapping(value = "/person/roles/{id}", method = RequestMethod.GET)
    public List<Roles> getPersonRolesByNameAndPassword(@PathVariable("id") Long id) {
        return usersService.findById(id).getRoles();
    }
}
