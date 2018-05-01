package com.day0ff.news.controller;

import com.day0ff.news.entity.*;
import com.day0ff.news.service.*;
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
 * The class controller realizes the business logic, separates the methods available for users with roles "EDITOR".
 */
@RestController
@RequestMapping("/editor")
public class EditorController {
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
     * property - set CommentsService bean
     */
    @Autowired
    private CommentsService commentsService;
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
     * The method save new News
     *
     * @param personId        author id
     * @param title           news title
     * @param article         news article
     * @param image           news image
     * @param publicationDate news publication date
     * @param published       is the news published
     * @return News object
     */
    @RequestMapping(value = "/news/save", method = RequestMethod.POST)
    public News saveNews(@RequestParam("author") Long personId,
                         @RequestParam("title") String title,
                         @RequestParam("article") String article,
                         @RequestParam("post") String post,
                         @RequestParam("image") String image,
                         @RequestParam("publication_date") String publicationDate,
                         @RequestParam("published") Boolean published
    ) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.save.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = new News();
        news.setPerson(personsService.findById(personId));
        news.setTitle(title);
        news.setArticle(article);
        news.setPost(post);
        news.setImage(image);
        news.setPublicationDateString(publicationDate);
        news.setViews(0);
        news.setPublished(published);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.save.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.save(news);
    }

    /**
     * The method delete the News
     *
     * @param newsId news id
     */
    @RequestMapping(value = "/news/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNews(@RequestParam("news_id") Long newsId) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.delete.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = newsService.findById(newsId);
        news.setCategories(null);
        news.setTags(null);
        newsService.save(news);
        List<Likes> likes = likesService.findByNews(newsId);
        likes.forEach(like -> likesService.delete(like));
        List<Comments> comments = commentsService.getNewsComments(newsId);
        comments.forEach(comment -> commentsService.delete(comment.getId()));
        newsService.delete(newsId);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.delete.news", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method update the News
     *
     * @param newsId          news id
     * @param personId        author id
     * @param title           news title
     * @param article         news article
     * @param image           news image
     * @param publicationDate news publication date
     * @param published       is the news published
     * @return News object
     */
    @RequestMapping(value = "/news/update", method = RequestMethod.POST)
    public News updateNews(
            @RequestParam("id") Long newsId,
            @RequestParam("author") Long personId,
            @RequestParam("title") String title,
            @RequestParam("article") String article,
            @RequestParam("post") String post,
            @RequestParam("image") String image,
            @RequestParam("publication_date") String publicationDate,
            @RequestParam("published") Boolean published
    ) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = newsService.findById(newsId);
        news.setPerson(personsService.findById(personId));
        news.setTitle(title);
        news.setArticle(article);
        news.setPost(post);
        news.setImage(image);
        news.setPublicationDateString(publicationDate);
        news.setPublished(published);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.update", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return newsService.save(news);
    }

    /**
     * The method delete the Comment
     *
     * @param commentId comment id
     */
    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestParam("id") Long commentId) {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.comment.delete", null, "locale not found", Locale.getDefault());
        logger.info(message);
        commentsService.delete(commentId);
    }

    /**
     * The method add Categories to the News
     *
     * @param newsId       news id
     * @param categoryList news categories
     */
    @RequestMapping(value = "/news/categories/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addNewsCategories(@RequestParam(value = "newsId", required = false) Long newsId,
                                  @RequestParam(value = "categoryId", required = false) List<Long> categoryList) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.categories.add", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = newsService.findById(newsId);
        List<Categories> categories = new ArrayList<>();
        categoryList.forEach(id -> categories.add(categoriesService.findById(id)));
        news.setCategories(categories);
        newsService.save(news);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.categories.add", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method delete Category from the News
     *
     * @param newsId     news id
     * @param categoryId news category id
     */
    @RequestMapping(value = "/news/categories/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNewsCategories(@RequestParam("newsId") Long newsId,
                                     @RequestParam("categoryId") Long categoryId) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.categories.delete", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = newsService.fetchNewsCategoriesFindById(newsId);
        news.setCategories(
                news.getCategories().stream()
                        .filter(category -> !(category.getId().equals(categoryId)))
                        .collect(Collectors.toList())
        );
        newsService.save(news);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.categories.delete", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method delete all Categories from the News
     *
     * @param newsId news id
     */
    @RequestMapping(value = "/news/categories/deleteAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllNewsCategories(@RequestParam("newsId") Long newsId) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.categories.delete.all", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = newsService.fetchNewsCategoriesFindById(newsId);
        news.setCategories(null);
        newsService.save(news);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.categories.delete.all", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method save new Tag
     *
     * @param newTag tag name
     * @return Tag object
     */
    @RequestMapping(value = "/tag/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Tags saveNewTag(@RequestParam("newTag") String newTag) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.tag.new", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Tags tag = new Tags();
        tag.setTag(newTag);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.tag.new", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return tagsService.save(tag);
    }

    /**
     * The method add Tags to the News
     *
     * @param newsId  news id
     * @param tagList news tags
     * @return Tag object
     */
    @RequestMapping(value = "/news/tags/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addNewsTags(@RequestParam(value = "newsId", required = false) Long newsId,
                            @RequestParam(value = "tagId", required = false) List<Long> tagList) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.tags.add", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = newsService.findById(newsId);
        List<Tags> tags = new ArrayList<>();
        tagList.forEach(id -> tags.add(tagsService.findById(id)));
        news.setTags(tags);
        newsService.save(news);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.tags.add", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method delete all Tags from the News
     *
     * @param newsId news id
     */
    @RequestMapping(value = "/news/tags/deleteAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllNewsTags(@RequestParam("newsId") Long newsId) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.tags.delete.all", null, "locale not found", Locale.getDefault());
        logger.info(message);
        News news = newsService.fetchFindById(newsId);
        news.setTags(null);
        newsService.save(news);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("editor.news.tags.delete.all", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }
}
