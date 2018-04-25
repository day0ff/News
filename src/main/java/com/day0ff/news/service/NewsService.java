package com.day0ff.news.service;

import com.day0ff.news.entity.News;

import java.util.List;

public interface NewsService {
    News save(News news);
    News update(News news);
    void delete(Long id);
    List<News> findAll();
    News findById(Long id);
    News fetchFindById(Long id);
    News fetchNewsCategoriesFindById(Long id);
    List<News> fetchNewsFindByPersonId(Long id);
    int fetchNewsCountFindByPersonId(Long id);
    List<News> findNewsByCategories(String category);
    List<News> getNewsFindByTagId(Long id);
    List<News> getNewsFindByTagTitle(String title);
    void incrementNewsViews(Long newsId);
}
