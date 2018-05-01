package com.day0ff.news.controller;


import com.day0ff.news.entity.*;
import com.day0ff.news.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * The class controller realizes the business logic, separates the methods available for users with roles "USER".
 */
@RestController
@RequestMapping("/user")
public class UserConttroller {
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
     * property - set LikesService bean
     */
    @Autowired
    private LikesService likesService;
    /**
     * property - set NewsService bean
     */
    @Autowired
    private NewsService newsService;
    /**
     * property - set CommentsService bean
     */
    @Autowired
    private CommentsService commentsService;

    /**
     * The method request for the Person by name
     *
     * @param name person name
     * @return Person object
     */
    @RequestMapping(value = "/person/name", method = RequestMethod.POST)
    public Persons getPersonByName(@RequestParam("name") String name) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.person.name", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return personsService.findByName(name);
    }

    /**
     * The method request for the Person by name
     *
     * @param id person id
     * @return Person object
     */
    @RequestMapping(value = "/person/id", method = RequestMethod.POST)
    public Persons getPersonById(@RequestParam("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.person.id", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return personsService.findById(id);
    }

    /**
     * The method request for the User by name and password
     *
     * @param name     user name
     * @param password user password
     * @return User object
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Users getUserByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return usersService.findByNameAndPassword(name, password);
    }
    /**
     * The method request for the User Roles by User name and password
     *
     * @param name     user name
     * @param password user password
     * @return roles array
     */
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public List<String> getPersonRolesByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.roles", null, "locale not found", Locale.getDefault());
        logger.info(message);
        List<Roles> roles = usersService.findByNameAndPassword(name, password).getRoles();
        return roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }
    /**
     * The method request for the User Roles by User name and password
     *
     * @param name     user name
     * @param password user password
     * @return Person object
     */
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Persons getPersonByNameAndPassword(@RequestParam("name") String name, @RequestParam("password") String password) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return personsService.findByNameAndPassword(name, password);
    }
    /**
     * The method save Person Like to the News.
     *
     * @param newsId    news id
     * @param personId  person id
     * @return Like object
     */
    @RequestMapping(value = "/like/save", method = RequestMethod.POST)
    public Likes saveLike(@RequestParam("news_id") Long newsId, @RequestParam("person_id") Long personId) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.like.save", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Likes like = new Likes();
        like.setNews(newsService.findById(newsId));
        like.getNews().getPerson().setUser(null);
        like.setPerson(personsService.findById(personId));
        like.getPerson().setUser(null);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.like.save", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return likesService.save(like);
    }
    /**
     * The method delete Person Like to the News.
     *
     * @param newsId    news id
     * @param personId  person id
     */
    @RequestMapping(value = "/like/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteLike(@RequestParam("news_id") Long newsId, @RequestParam("person_id") Long personId) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.like.delete", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Likes like = likesService.findByNewsAndPerson(newsId, personId);
        likesService.delete(like);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.like.delete", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }
    /**
     * The method save Person Comment to the News.
     *
     * @param newsId    news id
     * @param personId  person id
     * @param comment   person comment ot the news
     * @return Like object
     */
    @RequestMapping(value = "/comment/save", method = RequestMethod.POST)
    public Comments saveComment(@RequestParam("person_id") Long personId,
                                @RequestParam("news_id") Long newsId,
                                @RequestParam("comment") String comment) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.comment.save", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Comments comments = new Comments();
        comments.setPerson(personsService.findById(personId));
        comments.getPerson().setUser(null);
        comments.setNews(newsService.findById(newsId));
        comments.getNews().getPerson().setUser(null);
        comments.setComment(comment);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.comment.save", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return commentsService.save(comments);
    }
    /**
     * The method update the User by User name and password.
     *
     * @param userName    user old name
     * @param password    user old password
     * @param newName     user new name
     * @param newPassword user new password
     * @return User object
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Users savePerson(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("newName") String newName,
            @RequestParam("newPassword") String newPassword
    ) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.user.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Users user = usersService.findByNameAndPassword(userName, password);
        user.setUserName(newName);
        user.setPassword(newPassword);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.user.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return usersService.save(user);
    }
    /**
     * The method update the Person by User name and password.
     *
     * @param userName    user old name
     * @param password    user old password
     * @param firstName  person first name
     * @param lastName   person last name
     * @param screenName person screen name
     * @param image      person image
     * @return Person object
     */
    @RequestMapping(value = "/person/update", method = RequestMethod.POST)
    public Persons savePerson(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("screenName") String screenName,
            @RequestParam("image") String image
    ) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.person.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Persons person = personsService.findByNameAndPassword(userName, password);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setScreenName(screenName);
        person.setImage(image);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.person.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return personsService.save(person);
    }
    /**
     * The method delete Person by User name and password.
     *
     * @param userName    user old name
     * @param password    user old password
     * @param personId  person id
     */
    @RequestMapping(value = "/person/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("personId") Long personId
    ) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.person.delete", null, "locale not found", Locale.getDefault());
        logger.info(message);
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
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("user.person.delete", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }
}
