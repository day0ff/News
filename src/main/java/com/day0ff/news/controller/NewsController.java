package com.day0ff.news.controller;


import com.day0ff.news.entity.Comments;
import com.day0ff.news.entity.Likes;
import com.day0ff.news.entity.News;
import com.day0ff.news.service.CommentsService;
import com.day0ff.news.service.LikesService;
import com.day0ff.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private LikesService likesService;

    @RequestMapping(value = "likes", method = RequestMethod.GET)
    public List<Likes> getLikes() {
        return likesService.findAll();
    }

    @RequestMapping(value = "likes/count/news/{id}", method = RequestMethod.GET)
    public int getLikesCountNews(@PathVariable("id")  Long id) {
        return likesService.getCountNewsLikes(id);
    }

    @RequestMapping(value = "likes/count/person/{id}", method = RequestMethod.GET)
    public int getLikesCountPerson(@PathVariable("id")  Long id) {
        return likesService.getCountPersonLikes(id);
    }

    @RequestMapping(value = "likes/{personId}/{newsId}", method = RequestMethod.GET)
    public int isPersonLikeNews(@PathVariable("personId")  Long personId, @PathVariable("newsId")  Long newsId) {
        return likesService.isPersonLikeNews(personId, newsId);
    }

    @RequestMapping(value = "news", method = RequestMethod.GET)
    public List<News> getNews() {
        return newsService.findAll().stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/tag/{id}", method = RequestMethod.GET)
    public List<News> getNewsByTagId(@PathVariable("id")  Long id) {
        return newsService.getNewsFindByTagId(id).stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/tag/title/{title}", method = RequestMethod.GET)
    public List<News> getNewsByTagTitle(@PathVariable("title")  String title) {
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

    @RequestMapping(value = "comments/person/count/{id}", method = RequestMethod.GET)
    public int getCommentsPersonCount(@PathVariable("id") Long id) {
        return commentsService.getCountPersonComments(id);
    }

    @RequestMapping(value = "comments/news/count/{id}", method = RequestMethod.GET)
    public int getCommentsNewsCount(@PathVariable("id") Long id) {
        return commentsService.getCountNewsComments(id);
    }
}
