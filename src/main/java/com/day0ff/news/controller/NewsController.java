package com.day0ff.news.controller;


import com.day0ff.news.entity.*;
import com.day0ff.news.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private PersonsService personsService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public List<Categories> getCategories() {
        return categoriesService.findAll();
    }

    @RequestMapping(value = "likes", method = RequestMethod.GET)
    public List<Likes> getLikes() {
        return likesService.findAll();
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public List<Roles> getRoles() {
        return rolesService.findAll();
    }

    @RequestMapping(value = "news/likes/count/{id}", method = RequestMethod.GET)
    public int getLikesCountNews(@PathVariable("id") Long id) {
        return likesService.getCountNewsLikes(id);
    }

    @RequestMapping(value = "likes/count/person/{id}", method = RequestMethod.GET)
    public int getLikesCountPerson(@PathVariable("id") Long id) {
        return likesService.getCountPersonLikes(id);
    }

    @RequestMapping(value = "likes/{newsId}/{personId}", method = RequestMethod.GET)
    public int isPersonLikeNews(@PathVariable("newsId") Long newsId, @PathVariable("personId") Long personId) {
        return likesService.isPersonLikeNews(newsId, personId);
    }

    @RequestMapping(value = "like/{newsId}/{personId}", method = RequestMethod.GET)
    public Likes getLikeByNewsAndPerson(@PathVariable("newsId") Long newsId, @PathVariable("personId") Long personId) {
        Likes like = likesService.findByNewsAndPerson(newsId, personId);
        like.getNews().getPerson().setUser(null);
        like.getPerson().setUser(null);
        return like;
    }

    @RequestMapping(value = "news", method = RequestMethod.GET)
    public List<News> getNews() {
        return newsService.findAll().stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/{newsId}", method = RequestMethod.GET)
    public News getNewsById(@PathVariable("newsId") Long newsId) {
        News news = newsService.findById(newsId);
        news.getPerson().setUser(null);
        return news;
    }

    @RequestMapping(value = "tags", method = RequestMethod.GET)
    public List<Tags> getTagsByNewsId() {
        return tagsService.findAll();
    }

    @RequestMapping(value = "tags/news/{newsId}", method = RequestMethod.GET)
    public List<Tags> getTagsByNewsId(@PathVariable("newsId") Long newsId) {
        return newsService.fetchFindById(newsId).getTags();
    }

    @RequestMapping(value = "category/news/{id}", method = RequestMethod.GET)
    public List<Categories> getNewsCategoryById(@PathVariable("id") Long id) {
        return newsService.fetchNewsCategoriesFindById(id).getCategories();
    }

    @RequestMapping(value = "news/category/{category}", method = RequestMethod.GET)
    public List<News> getNewsByCategory(@PathVariable("category") String category) {
        return newsService.findNewsByCategories(category).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/category/{category}/{limit}", method = RequestMethod.GET)
    public List<News> getNewsByCategoryLimit(@PathVariable("category") String category, @PathVariable("limit") Integer limit) {
        return newsService.findNewsByCategories(category).stream()
                .peek(news -> news.getPerson().setUser(null))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/person/{personId}", method = RequestMethod.GET)
    public List<News> getNewsByPersonId(@PathVariable("personId") Long personId) {
        return newsService.fetchNewsFindByPersonId(personId).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/tag/{id}", method = RequestMethod.GET)
    public List<News> getNewsByTagId(@PathVariable("id") Long id) {
        return newsService.getNewsFindByTagId(id).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/tag/title/{title}", method = RequestMethod.GET)
    public List<News> getNewsByTagTitle(@PathVariable("title") String title) {
        return newsService.getNewsFindByTagTitle(title).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/comments/count/{id}", method = RequestMethod.GET)
    public int getNewsCommentsCount(@PathVariable("id") Long id) {
        return commentsService.getCountNewsComments(id);
    }

    @RequestMapping(value = "news/person/count/{id}", method = RequestMethod.GET)
    public int getNewsPersonCount(@PathVariable("id") Long id) {
        return newsService.fetchNewsCountFindByPersonId(id);
    }

    @RequestMapping(value = "comments", method = RequestMethod.GET)
    public List<Comments> getComments() {
        return commentsService.findAll().stream()
                .peek(comment -> comment.getPerson().setUser(null))
                .peek(comment -> comment.getNews().setPerson(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "comments/news/{id}", method = RequestMethod.GET)
    public List<Comments> getNewsComments(@PathVariable("id") Long id) {
        return commentsService.findCommentsByNews_Id(id).stream()
                .peek(comment -> comment.getPerson().setUser(null))
                .peek(comment -> comment.getNews().setPerson(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "comments/person/{id}", method = RequestMethod.GET)
    public List<Comments> getPersonComments(@PathVariable("id") Long id) {
        return commentsService.findCommentsByPerson_Id(id).stream()
                .peek(comment -> comment.getPerson().setUser(null))
                .peek(comment -> comment.getNews().setPerson(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "comments/person/count/{id}", method = RequestMethod.GET)
    public int getCommentsPersonCount(@PathVariable("id") Long id) {
        return commentsService.getCountPersonComments(id);
    }

    @RequestMapping(value = "comments/news/count/{id}", method = RequestMethod.GET)
    public int getCommentsNewsCount(@PathVariable("id") Long id) {
        return commentsService.getCountNewsComments(id);
    }

/*    @RequestMapping(value = "like/save", method = RequestMethod.POST)
    public Likes saveLike(@RequestParam("news_id") Long newsId, @RequestParam("person_id") Long personId) {
        Likes like = new Likes();
        like.setNews(newsService.findById(newsId));
        like.getNews().getPerson().setUser(null);
        like.setPerson(personsService.findById(personId));
        like.getPerson().setUser(null);
        return likesService.save(like);
    }

    @RequestMapping(value = "like/delete", method = RequestMethod.POST)
    public void deleteLike(@RequestParam("news_id") Long newsId, @RequestParam("person_id") Long personId) {
        Likes like = likesService.findByNewsAndPerson(newsId, personId);
        likesService.delete(like);
    }*/

    @RequestMapping(value = "news/views/{newsId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void incrementNewsViews(@PathVariable("newsId") Long newsId) {
        newsService.incrementNewsViews(newsId);
    }

/*    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestParam("id") Long commentId) {
        System.out.println("Comment id = "  + commentId);
        commentsService.delete(commentId);
    }*/

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestParam("userName") String userName,
                                   @RequestParam("password") String password) {
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
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FOUND);
    }

    @RequestMapping(value = "/users/save/person", method = RequestMethod.POST)
    public Persons saveUserPerson(@RequestParam("userName") String userName,
                                  @RequestParam("password") String password,
                                  @RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("screenName") String screenName,
                                  @RequestParam("image") String image
    ) {
        Users user = usersService.findByNameAndPassword(userName, password);
        Persons person = new Persons();
        person.setUser(user);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setScreenName(screenName);
        person.setImage(image);
        return personsService.save(person);
    }
}
