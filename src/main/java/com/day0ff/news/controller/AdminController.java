package com.day0ff.news.controller;

import com.day0ff.news.entity.*;
import com.day0ff.news.service.NewsService;
import com.day0ff.news.service.PersonsService;
import com.day0ff.news.service.RolesService;
import com.day0ff.news.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * The class controller realizes the business logic, separates the methods available for users with roles "ADMIN".
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * property - set logger
     */
    final static Logger logger = LoggerFactory.getLogger(AdminController.class);
    /**
     * property - set MessageSource bean
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * property - set UsersService bean
     */
    @Autowired
    private UsersService usersService;
    /**
     * property - set PersonsService bean
     */
    @Autowired
    private PersonsService personsService;
    /**
     * property - set RolesService bean
     */
    @Autowired
    private RolesService rolesService;

    /**
     * The method request for all Persons
     *
     * @return Persons objects
     */
    @RequestMapping(value = "/persons", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Persons> getPerson() {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.persons", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return personsService.findAll();
    }

    /**
     * The method request for the Person by id
     *
     * @param id person id
     * @return Person object
     */
    @RequestMapping(value = "/person/get/{id}", method = RequestMethod.GET)
    public Persons getPersonById(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.person.get", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return personsService.findById(id);
    }

    /**
     * The method update the Person
     *
     * @param personId   person id
     * @param firstName  person first name
     * @param lastName   person last name
     * @param screenName person screen name
     * @param image      person image
     * @return Person object
     */
    @RequestMapping(value = "/person/update", method = RequestMethod.POST)
    public Persons savePerson(
            @RequestParam("personId") Long personId,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("screenName") String screenName,
            @RequestParam("image") String image
    ) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.person.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Persons person = personsService.findById(personId);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setScreenName(screenName);
        person.setImage(image);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.person.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return personsService.save(person);
    }

    /**
     * The method update the User by id
     *
     * @param userId      user id
     * @param newName     user new name
     * @param newPassword user new password
     * @return User object
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Users saveUser(
            @RequestParam("userId") Long userId,
            @RequestParam("newName") String newName,
            @RequestParam("newPassword") String newPassword
    ) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.user.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Users user = usersService.findById(userId);
        user.setUserName(newName);
        user.setPassword(newPassword);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.user.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return usersService.save(user);
    }

    /**
     * The method request for the Person Roles by Person id
     *
     * @param id person id
     * @return Roles objects
     */
    @RequestMapping(value = "/person/roles/{id}", method = RequestMethod.GET)
    public List<Roles> getPersonRolesByNameAndPassword(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.person.roles", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return usersService.findById(id).getRoles();
    }

    /**
     * The method add Roles to the User
     *
     * @param userId    user id
     * @param rolesList user roles
     */
    @RequestMapping(value = "/user/roles/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addNewsTags(@RequestParam(value = "userId", required = false) Long userId,
                            @RequestParam(value = "roleId", required = false) List<Long> rolesList) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.user.roles.add", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Users user = usersService.findById(userId);
        List<Roles> tags = new ArrayList<>();
        rolesList.forEach(id -> tags.add(rolesService.findById(id)));
        user.setRoles(tags);
        usersService.save(user);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("admin.user.roles.add", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }
}
