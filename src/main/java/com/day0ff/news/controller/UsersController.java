package com.day0ff.news.controller;

import com.day0ff.news.entity.*;
import com.day0ff.news.service.*;
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

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Users getUserById(@PathVariable("id") Long id) {
        return usersService.findById(id);
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    public List<Roles> getUserRolesById(@PathVariable("id") Long id) {
        Users user = usersService.findById(id);
        return user.getRoles();
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
}
