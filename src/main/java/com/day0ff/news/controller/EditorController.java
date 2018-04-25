package com.day0ff.news.controller;

import com.day0ff.news.entity.*;
import com.day0ff.news.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/editor")
public class EditorController {
    @Autowired
    private CommentsService commentsService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private PersonsService personsService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private TagsService tagsService;

    @RequestMapping(value = "/news/save", method = RequestMethod.POST)
    public News saveNews(@RequestParam("author") Long personId,
                         @RequestParam("title") String title,
                         @RequestParam("article") String article,
                         @RequestParam("post") String post,
                         @RequestParam("image") String image,
                         @RequestParam("publication_date") String publicationDate,
                         @RequestParam("published") Boolean published
                        ) {
        News news = new News();
        news.setPerson(personsService.findById(personId));
        news.setTitle(title);
        news.setArticle(article);
        news.setPost(post);
        news.setImage(image);
        news.setPublicationDateString(publicationDate);
        news.setViews(0);
        news.setPublished(published);
        return newsService.save(news);
    }

    @RequestMapping(value = "/news/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNews(@RequestParam("news_id") Long newsId) {
        News news = newsService.findById(newsId);
        news.setCategories(null);
        news.setTags(null);
        newsService.save(news);
        List<Likes> likes = likesService.findByNews(newsId);
        likes.forEach(like -> likesService.delete(like));
        List<Comments> comments = commentsService.getNewsComments(newsId);
        comments.forEach(comment -> commentsService.delete(comment.getId()));
        newsService.delete(newsId);
    }

    @RequestMapping(value = "/news/update", method = RequestMethod.POST)
    public News saveNews(
                           @RequestParam("id") Long newsId,
                           @RequestParam("author") Long personId,
                           @RequestParam("title") String title,
                           @RequestParam("article") String article,
                           @RequestParam("post") String post,
                           @RequestParam("image") String image,
                           @RequestParam("publication_date") String publicationDate,
                           @RequestParam("published") Boolean published
    ) {
        News news = newsService.findById(newsId);
        news.setPerson(personsService.findById(personId));
        news.setTitle(title);
        news.setArticle(article);
        news.setPost(post);
        news.setImage(image);
        news.setPublicationDateString(publicationDate);
        news.setPublished(published);
        return newsService.save(news);
    }

    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestParam("id") Long commentId) {
        commentsService.delete(commentId);
    }


    @RequestMapping(value = "/news/categories/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void saveNewsCategories(@RequestParam("newsId") Long newsId,
                                   @RequestParam("categoryId") Long categoryId) {
        News news;
        List<Categories> categories;
        try {
            news = newsService.fetchNewsCategoriesFindById(newsId);
            categories = news.getCategories();

        } catch (Exception e){
            news = newsService.findById(newsId);
            categories = new ArrayList<>();
        }
        Categories category = categoriesService.findById(categoryId);
        categories.add(category);
        news.setCategories(categories);
        newsService.save(news);
    }

    @RequestMapping(value = "/news/categories/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addNewsCategories(@RequestParam(value="newsId", required=false) Long newsId,
                                  @RequestParam(value="categoryId", required=false) List<Long> categoryList) {
        News news;
        List<Categories> categories = new ArrayList<>();
        try {
            news = newsService.fetchNewsCategoriesFindById(newsId);
        } catch (Exception e){
            news = newsService.findById(newsId);
        }
        categoryList.forEach(id -> categories.add(categoriesService.findById(id)) );
        news.setCategories(categories);
        newsService.save(news);
    }

    @RequestMapping(value = "/news/categories/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNewsCategories(@RequestParam("newsId") Long newsId,
                                     @RequestParam("categoryId") Long categoryId) {
        News news = newsService.fetchNewsCategoriesFindById(newsId);
        news.setCategories(
                news.getCategories().stream()
                        .filter(category -> !(category.getId().equals(categoryId)))
                        .collect(Collectors.toList())
        );
        newsService.save(news);
    }

    @RequestMapping(value = "/news/categories/deleteAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllNewsCategories(@RequestParam("newsId") Long newsId) {
        News news = newsService.fetchNewsCategoriesFindById(newsId);
        news.setCategories(null);
        newsService.save(news);
    }

    @RequestMapping(value = "/news/tags/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void saveNewsTags(@RequestParam("newsId") Long newsId,
                                   @RequestParam("tagId") Long tagId) {
        News news;
        List<Tags> tags;
        try {
            news = newsService.fetchFindById(newsId);
            tags = news.getTags();

        } catch (Exception e){
            news = newsService.findById(newsId);
            tags = new ArrayList<>();
        }
        Tags tag = tagsService.findById(tagId);
        tags.add(tag);
        news.setTags(tags);
        newsService.save(news);
    }

    @RequestMapping(value = "/news/tags/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addNewsTags(@RequestParam(value="newsId", required=false) Long newsId,
                                  @RequestParam(value="tagId", required=false) List<Long> categoryList) {
        News news;
        List<Tags> tags = new ArrayList<>();
        try {
            news = newsService.fetchNewsCategoriesFindById(newsId);
        } catch (Exception e){
            news = newsService.findById(newsId);
        }
        categoryList.forEach(id -> tags.add(tagsService.findById(id)) );
        news.setTags(tags);
        newsService.save(news);
    }

    @RequestMapping(value = "/news/tags/deleteAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllNewsTags(@RequestParam("newsId") Long newsId) {
        News news = newsService.fetchFindById(newsId);
        news.setTags(null);
        newsService.save(news);
    }

/*    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestParam("id") Long commentId) {
        Comments comment = commentsService.findById(commentId);
        commentsService.delete(comment);
    }*/
}
