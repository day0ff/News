package com.day0ff.news.controller;


import com.day0ff.news.entity.Comments;
import com.day0ff.news.entity.News;
import com.day0ff.news.service.CommentsService;
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

    @RequestMapping(value = "news", method = RequestMethod.GET)
    public List<News> getNews() {
        return newsService.findAll().stream()
                .peek(news -> news.getPerson().setUser(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "news/count/{id}", method = RequestMethod.GET)
    public int getNewsCommentsCount(@PathVariable("id") Long id) {
        return commentsService.getCountPersonComments(id);
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
    public int getPersonCommentsCount(@PathVariable("id") Long id) {
        return commentsService.getCountPersonComments(id);
    }

}
