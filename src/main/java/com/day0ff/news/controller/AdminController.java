package com.day0ff.news.controller;

import com.day0ff.news.entity.*;
import com.day0ff.news.service.NewsService;
import com.day0ff.news.service.PersonsService;
import com.day0ff.news.service.RolesService;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private PersonsService personsService;

    @Autowired
    private RolesService rolesService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Persons> getPerson() {
        return personsService.findAll();
    }

    @RequestMapping(value = "/person/get/{id}", method = RequestMethod.GET)
    public Persons getPersonById(@PathVariable("id") Long id) {
        return personsService.findById(id);
    }

    @RequestMapping(value = "/person/update", method = RequestMethod.POST)
    public Persons savePerson(
            @RequestParam("personId") Long personId,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("screenName") String screenName,
            @RequestParam("image") String image
    ) {
        Persons person = personsService.findById(personId);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setScreenName(screenName);
        person.setImage(image);
        return personsService.save(person);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Users savePerson(
            @RequestParam("userId") Long userId,
            @RequestParam("newName") String newName,
            @RequestParam("newPassword") String newPassword
    ) {
        Users user = usersService.findById(userId);
        user.setUserName(newName);
        user.setPassword(newPassword);
        return usersService.save(user);
    }

    @RequestMapping(value = "/person/roles/{id}", method = RequestMethod.GET)
    public List<Roles> getPersonRolesByNameAndPassword(@PathVariable("id") Long id) {
        return usersService.findById(id).getRoles();
    }

    @RequestMapping(value = "/user/roles/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addNewsTags(@RequestParam(value = "userId", required = false) Long userId,
                            @RequestParam(value = "roleId", required = false) List<Long> rolesList) {
        Users user = usersService.findById(userId);
        List<Roles> tags = new ArrayList<>();
        rolesList.forEach(id -> tags.add(rolesService.findById(id)));
        user.setRoles(tags);
        usersService.save(user);
    }
}
