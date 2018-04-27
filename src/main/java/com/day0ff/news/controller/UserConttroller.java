package com.day0ff.news.controller;


import com.day0ff.news.entity.*;
import com.day0ff.news.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private LikesService likesService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentsService commentsService;

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

/*    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public List<String> getPersonRolesByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        List<Roles> roles = usersService.findByNameAndPassword(name, password).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }*/

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public List<String> getPersonRolesByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        List<Roles> roles = usersService.findByNameAndPassword(name, password).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Persons getPersonByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        return personsService.findByNameAndPassword(name, password);
    }

    @RequestMapping(value = "/like/save", method = RequestMethod.POST)
    public Likes saveLike(@RequestParam("news_id") Long newsId, @RequestParam("person_id") Long personId) {
        Likes like = new Likes();
        like.setNews(newsService.findById(newsId));
        like.getNews().getPerson().setUser(null);
        like.setPerson(personsService.findById(personId));
        like.getPerson().setUser(null);
        return likesService.save(like);
    }

    @RequestMapping(value = "/like/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteLike(@RequestParam("news_id") Long newsId, @RequestParam("person_id") Long personId) {
        Likes like = likesService.findByNewsAndPerson(newsId, personId);
        likesService.delete(like);
    }

    @RequestMapping(value = "/comment/save", method = RequestMethod.POST)
    public Comments saveComment(@RequestParam("person_id") Long personId,
                                @RequestParam("news_id") Long newsId,
                                @RequestParam("comment") String comment) {
        Comments comments = new Comments();
        comments.setPerson(personsService.findById(personId));
        comments.getPerson().setUser(null);
        comments.setNews(newsService.findById(newsId));
        comments.getNews().getPerson().setUser(null);
        comments.setComment(comment);
        return commentsService.save(comments);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Users savePerson(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("newName") String newName,
            @RequestParam("newPassword") String newPassword
    ) {
        Users user = usersService.findByNameAndPassword(userName, password);
        user.setUserName(newName);
        user.setPassword(newPassword);
        return usersService.save(user);
    }

    @RequestMapping(value = "/person/update", method = RequestMethod.POST)
    public Persons savePerson(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("screenName") String screenName,
            @RequestParam("image") String image
    ) {
        Persons person = personsService.findByNameAndPassword(userName, password);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setScreenName(screenName);
        person.setImage(image);
        return personsService.save(person);
    }

    @RequestMapping(value = "/person/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("personId") Long personId
    ) {
        List<News> news = newsService.fetchNewsFindByPersonId(personId);
        news.forEach(currentNews -> {
            currentNews.setCategories(null);
            currentNews.setTags(null);
            newsService.save(currentNews);
            List<Likes> likes = likesService.findByNews(currentNews.getId());
            likes.forEach(like -> likesService.delete(like));
            List<Comments> comments = commentsService.getNewsComments(currentNews.getId());
            comments.forEach(comment -> commentsService.delete(comment.getId()));
            newsService.delete(currentNews.getId());
        });
        Persons person = personsService.findByNameAndPassword(userName, password);
        person.setUser(null);
        List<Likes> likes = likesService.findByPersonId(personId);
        likes.forEach(like -> likesService.delete(like));
        List<Comments> comments = commentsService.getPersonComments(personId);
        comments.forEach(comment -> commentsService.delete(comment.getId()));
        personsService.delete(personId);
        Users user = usersService.findByName(userName);
        user.setRoles(null);
        usersService.delete(user);
    }
}
