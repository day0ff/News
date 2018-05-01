package com.day0ff.news.service.impl;

import com.day0ff.news.entity.News;
import com.day0ff.news.repository.NewsRepository;
import com.day0ff.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * The class implements methods of the business logic entities of entity News.
 */
@Service
public class NewsImpl implements NewsService {
    /**
     * property - set NewsRepository bean
     */
    @Autowired
    private NewsRepository newsRepository;
    /**
     * The method save News in the database
     *
     * @return News object
     */
    @Override
    public News save(News news) {
        return newsRepository.saveAndFlush(news);
    }
    /**
     * The method delete News object from database by id
     */
    @Override
    public void delete(Long id) {
        newsRepository.delete(id);
    }
    /**
     * The method find all News objects in the database
     *
     * @return News objects list
     */
    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }
    /**
     * The method increment News views by one
     *
     */
    @Transactional(readOnly = true)
    @Override
    public void incrementNewsViews(Long newsId) {
        newsRepository.incrementNewsViews(newsId);
    }
    /**
     * The method find News in the database by id
     *
     * @return News object
     */
    @Override
    public News findById(Long id) {
        return newsRepository.findById(id);
    }
    /**
     * The method find News in the database by id and ignored field Tags
     *
     * @return News object
     */
    @Override
    public News fetchFindById(Long id) {
        return newsRepository.fetchNewsFindById(id);
    }
    /**
     * The method find News in the database by id and ignored field Categories
     *
     * @return News object
     */
    @Override
    public News fetchNewsCategoriesFindById(Long id) {
        return newsRepository.fetchNewsCategoriesFindById(id);
    }
    /**
     * The method find News by Person id in the database and Person object
     *
     * @return News object
     */
    @Override
    public List<News> fetchNewsFindByPersonId(Long id) {
        return newsRepository.fetchNewsFindByPersonId(id);
    }
    /**
     * The method return Person published News count from database by Person id
     *
     * @return News count
     */
    @Override
    public int fetchNewsCountFindByPersonId(Long id) {
        return newsRepository.fetchNewsCountFindByPersonId(id);
    }
    /**
     * The method find News by Category in the database and ignored field Categories
     *
     * @return News object
     */
    @Override
    public List<News> findNewsByCategories(String category) {
        return newsRepository.findNewsByCategories(category);
    }
    /**
     * The method find News by Tags id in the database and ignored field Tags
     *
     * @return News object
     */
    @Override
    public List<News> getNewsFindByTagId(Long id) {
        return newsRepository.fetchNewsFindByTagId(id);
    }
    /**
     * The method find News by Tags title in the database and ignored field Tags
     *
     * @return News object
     */
    @Override
    public List<News> getNewsFindByTagTitle(String title) {
        return newsRepository.getNewsByTagTitle(title);
    }

}
