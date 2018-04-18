package com.day0ff.news.controller;

import com.day0ff.news.entity.*;
import com.day0ff.news.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
//@RequestMapping("/")
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private PersonsService personsService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private TagsServise tagsServise;
    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Users getUserById(@PathVariable("id") Long id) {
        return usersService.findById(id);
    }

    @RequestMapping(value = "/users/name/{name}", method = RequestMethod.GET)
    public Users getUserByName(@PathVariable("name")String userName) {
        return usersService.findByName(userName);
    }

    @RequestMapping(value = "/roles/id/{id}", method = RequestMethod.GET)
    public List<Roles> getUserRolesById(@PathVariable("id") Long id) {
        return usersService.findById(id).getRoles();
    }

    @RequestMapping(value = "/roles/name/{name}", method = RequestMethod.GET)
    public List<Roles> getUserRolesByName(@PathVariable("name")String userName) {
        return usersService.findByName(userName).getRoles();
    }

    @RequestMapping(value = "/persons/get", method = RequestMethod.GET)
    public List<Persons> getAllPersons() {
        return personsService.findAll();
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List<News> getAllNews() {
        return newsService.findAll();
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public News getNewsById(@PathVariable("id") Long id) {
        return newsService.findById(id);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public List<Tags> getAllTags() {
        return tagsServise.findAll();
    }

    @RequestMapping(value = "/tags/news/{id}", method = RequestMethod.GET)
    public List<Tags> getTagsByNewsId(@PathVariable("id") Long id) {
        return newsService.fetchFindById(id).getTags();
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public List<Comments> getAllComments() {
        return commentsService.findAll();
    }


    @RequestMapping(value = "/persons/get/{id}", method = RequestMethod.GET)
    public Persons getPersonGet(@PathVariable("id") Long id) {
        return personsService.findById(id);
    }

/*    @RequestMapping(value = "/persons/post", method = RequestMethod.POST)
    public Persons getPersonPost(@RequestBody Long id, UriComponentsBuilder ucBuilder) {
        return personsService.findById(id);
    }*/

    @RequestMapping(value = "/persons/id", method = RequestMethod.POST)
    public Persons getPersonParamsById(@RequestParam("id") Long id) {
        return personsService.findById(id);
    }

    @RequestMapping(value = "/persons/id/{id}", method = RequestMethod.POST)
    public Persons getPersonById(@PathVariable("id") Long id) {
        return personsService.findById(id);
    }

    @RequestMapping(value = "/persons/name/{name}", method = RequestMethod.POST)
    public Persons getPersonByName(@PathVariable("name") String name) {
        return personsService.findByName(name);
    }

    @RequestMapping(value = "/persons/name/get/{name}", method = RequestMethod.GET)
    public Persons getPersonGetByName(@PathVariable("name") String name) {
        return personsService.findByName(name);
    }

    @RequestMapping(value = "/persons/roles/id/{id}", method = RequestMethod.POST)
    public List<String> getPersonRoles(@PathVariable("id") Long id) {
        List<Roles> roles = usersService.findById(id).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }

    @RequestMapping(value = "/persons/name/roles/{name}", method = RequestMethod.POST)
    public List<String> getPersonRolesByName(@PathVariable("name") String name) {
        List<Roles> roles = usersService.findByName(name).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }

    @RequestMapping(value = "/persons/name/roles/get/{name}", method = RequestMethod.GET)
    public List<String> getPersonRolesGetByName(@PathVariable("name") String name) {
        List<Roles> roles = usersService.findByName(name).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }

/*    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());

        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }*/

}
