package com.day0ff.news.controller;


import com.day0ff.news.configuration.DataConfig;
import com.day0ff.news.entity.*;
import com.day0ff.news.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * The class controller realizes the business logic.
 */
@RestController
@RequestMapping("/")
public class NewsController {
    /**
     * property - set logger
     */
    final static Logger logger = LoggerFactory.getLogger(DataConfig.class);
    /**
     * property - set MessageSource bean
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * property - set NewsService bean
     */
    @Autowired
    private NewsService newsService;
    /**
     * property - set PersonsService bean
     */
    @Autowired
    private PersonsService personsService;
    /**
     * property - set CommentsService bean
     */
    @Autowired
    private CommentsService commentsService;
    /**
     * property - set LikesService bean
     */
    @Autowired
    private LikesService likesService;
    /**
     * property - set CategoriesService bean
     */
    @Autowired
    private CategoriesService categoriesService;
    /**
     * property - set TagsService bean
     */
    @Autowired
    private TagsService tagsService;
    /**
     * property - set RolesService bean
     */
    @Autowired
    private RolesService rolesService;
    /**
     * property - set UsersService bean
     */
    @Autowired
    private UsersService usersService;

    /**
     * The method request for all Categories
     *
     * @return Categories objects
     */
    @RequestMapping(value = "categories", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Categories> getCategories() {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.categories", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return categoriesService.findAll();
    }

    /**
     * The method request for all Likes
     *
     * @return Likes objects
     */
    @RequestMapping(value = "likes", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Likes> getLikes() {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.likes", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return likesService.findAll();
    }

    /**
     * The method request for all Roles
     *
     * @return Roles objects
     */
    @RequestMapping(value = "roles", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Roles> getRoles() {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.roles", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return rolesService.findAll();
    }

    /**
     * The method requests the number of likes from news
     *
     * @param id news id
     * @return number of likes
     */
    @RequestMapping(value = "news/likes/count/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public int getLikesCountNews(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.likes.count", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return likesService.getCountNewsLikes(id);
    }

    /**
     * The method requests the number of likes from person
     *
     * @param id person id
     * @return number of likes
     */
    @RequestMapping(value = "likes/count/person/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public int getLikesCountPerson(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.likes.count.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return likesService.getCountPersonLikes(id);
    }

    /**
     * The method requests whether the person likes the news
     *
     * @param newsId   news id
     * @param personId person id
     * @return number of likes
     */
    @RequestMapping(value = "likes/{newsId}/{personId}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public int isPersonLikeNews(@PathVariable("newsId") Long newsId, @PathVariable("personId") Long personId) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.likes.news.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return likesService.isPersonLikeNews(newsId, personId);
    }

    /**
     * The method requests the person like to news
     *
     * @param newsId   news id
     * @param personId person id
     * @return Like object
     */
    @RequestMapping(value = "like/{newsId}/{personId}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public Likes getLikeByNewsAndPerson(@PathVariable("newsId") Long newsId, @PathVariable("personId") Long personId) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.like.news.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Likes like = likesService.findByNewsAndPerson(newsId, personId);
        like.getNews().getPerson().setUser(null);
        like.getPerson().setUser(null);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.like.news.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return like;
    }

    /**
     * The method request for all News and hide User information.
     *
     * @return News objects
     */
    @RequestMapping(value = "news", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<News> getNews() {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.findAll().stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    /**
     * The method requests for the News and hide User information.
     *
     * @param newsId news id
     * @return News object
     */
    @RequestMapping(value = "news/{newsId}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public News getNewsById(@PathVariable("newsId") Long newsId) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = newsService.findById(newsId);
        news.getPerson().setUser(null);
        return news;
    }

    /**
     * The method request for all Tags.
     *
     * @return Tags objects
     */
    @RequestMapping(value = "tags", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Tags> getTags() {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.tags", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return tagsService.findAll();
    }

    /**
     * The method requests for the News Tags
     *
     * @param newsId news id
     * @return Tags objects
     */
    @RequestMapping(value = "tags/news/{newsId}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Tags> getTagsByNewsId(@PathVariable("newsId") Long newsId) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.tags.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.fetchFindById(newsId).getTags();
    }

    /**
     * The method requests for the News Categories
     *
     * @param id news id
     * @return Categories objects
     */
    @RequestMapping(value = "category/news/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Categories> getNewsCategoryById(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.category.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.fetchNewsCategoriesFindById(id).getCategories();
    }

    /**
     * The method requests for News by Category and hide User information.
     *
     * @param category news category
     * @return News objects
     */
    @RequestMapping(value = "news/category/{category}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<News> getNewsByCategory(@PathVariable("category") String category) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.category", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.findNewsByCategories(category).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    /**
     * The method requests for a Limited amount of News By Category Title and hide User information.
     *
     * @param category news category title
     * @param limit    limit the number of news
     * @return News objects
     */
    @RequestMapping(value = "news/category/{category}/{limit}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<News> getNewsByCategoryLimit(@PathVariable("category") String category, @PathVariable("limit") Integer limit) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.category.category.limit", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.findNewsByCategories(category).stream()
                .peek(news -> news.getPerson().setUser(null))
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * The method requests for a Person News and hide User information.
     *
     * @param personId person id
     * @return News objects
     */
    @RequestMapping(value = "news/person/{personId}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<News> getNewsByPersonId(@PathVariable("personId") Long personId) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.fetchNewsFindByPersonId(personId).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    /**
     * The method requests for a News by Tag id and hide User information.
     *
     * @param id tag id
     * @return News objects
     */
    @RequestMapping(value = "news/tag/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<News> getNewsByTagId(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.tag", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.getNewsFindByTagId(id).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    /**
     * The method requests for a News by Tag title and hide User information.
     *
     * @param title tag title
     * @return News objects
     */
    @RequestMapping(value = "news/tag/title/{title}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<News> getNewsByTagTitle(@PathVariable("title") String title) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.tag.title", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.getNewsFindByTagTitle(title).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    /**
     * The method requests the number of comments from news
     *
     * @param id news id
     * @return number of comments
     */
    @RequestMapping(value = "news/comments/count/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public int getNewsCommentsCount(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.comments.count", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return commentsService.getCountNewsComments(id);
    }

    /**
     * The method requests the number of person news
     *
     * @param id person id
     * @return number of person news
     */
    @RequestMapping(value = "news/person/count/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public int getNewsPersonCount(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.person.count", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.fetchNewsCountFindByPersonId(id);
    }

    /**
     * The method request for all Comments and hide User and Person information.
     *
     * @return Comments objects
     */
    @RequestMapping(value = "comments", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Comments> getComments() {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.comments", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return commentsService.findAll().stream()
                .peek(comment -> comment.getPerson().setUser(null))
                .peek(comment -> comment.getNews().setPerson(null))
                .collect(Collectors.toList());
    }

    /**
     * The method requests for the Comments to the News and hide User and Person information.
     *
     * @param id news id
     * @return Comments objects
     */
    @RequestMapping(value = "comments/news/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Comments> getNewsComments(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.comments.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return commentsService.findCommentsByNewsId(id).stream()
                .peek(comment -> comment.getPerson().setUser(null))
                .peek(comment -> comment.getNews().setPerson(null))
                .collect(Collectors.toList());
    }

    /**
     * The method requests for the Comments of the Person and hide User and Person information.
     *
     * @param id person id
     * @return Comments objects
     */
    @RequestMapping(value = "comments/person/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<Comments> getPersonComments(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.comments.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return commentsService.findCommentsByPersonId(id).stream()
                .peek(comment -> comment.getPerson().setUser(null))
                .peek(comment -> comment.getNews().setPerson(null))
                .collect(Collectors.toList());
    }

    /**
     * The method requests the number of person news
     *
     * @param id person id
     * @return number of person news
     */
    @RequestMapping(value = "comments/person/count/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public int getCommentsPersonCount(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.comments.person.count", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return commentsService.getCountPersonComments(id);
    }

    /**
     * The method requests the number of comments from news
     *
     * @param id news id
     * @return number of comments
     */
    @RequestMapping(value = "comments/news/count/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public int getCommentsNewsCount(@PathVariable("id") Long id) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.comments.news.count", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return commentsService.getCountNewsComments(id);
    }

    /**
     * The method increment the news views
     *
     * @param newsId news id
     */
    @RequestMapping(value = "news/views/{newsId}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    @ResponseStatus(HttpStatus.OK)
    public void incrementNewsViews(@PathVariable("newsId") Long newsId) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.news.views", null, "locale not found", Locale.getDefault());
        logger.info(message);
        newsService.incrementNewsViews(newsId);
    }

    /**
     * The method save new User if there is not exist. Else return message that found.
     *
     * @param userName user name
     * @param password user password
     * @return HttpStatus.OK
     */
    @RequestMapping(value = "/users/save", method = RequestMethod.POST, produces = {"application/json; charset=utf-8"})
    public ResponseEntity saveUser(@RequestParam("userName") String userName,
                                   @RequestParam("password") String password) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.users.save", null, "locale not found", Locale.getDefault());
        logger.info(message);
        if (usersService.getCountUserByUserName(userName) == 0) {
            Users user = new Users();
            user.setUserName(userName);
            user.setPassword(password);
            user.setEnabled(true);
            usersService.save(user);
            List<Roles> roles = new ArrayList<>();
            Roles role = rolesService.findById(1L);
            roles.add(role);
            user.setRoles(roles);
            usersService.save(user);
            message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                    + " " + messageSource.getMessage("news.users.save", null, "locale not found", Locale.getDefault());
            logger.info(message);
            return new ResponseEntity(HttpStatus.OK);
        }
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.users.save", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return new ResponseEntity(HttpStatus.FOUND);
    }

    /**
     * The method save new Person.
     *
     * @param userName   user name
     * @param password   user password
     * @param firstName  person first name
     * @param lastName   person last name
     * @param screenName person screen name
     * @param image      person image
     * @return Person object
     */
    @RequestMapping(value = "/users/save/person", method = RequestMethod.POST, produces = {"application/json; charset=utf-8"})
    public Persons saveUserPerson(@RequestParam("userName") String userName,
                                  @RequestParam("password") String password,
                                  @RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("screenName") String screenName,
                                  @RequestParam("image") String image
    ) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.users.save.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Users user = usersService.findByNameAndPassword(userName, password);
        Persons person = new Persons();
        person.setUser(user);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setScreenName(screenName);
        person.setImage(image);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("news.users.save.person", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return personsService.save(person);
    }
}
