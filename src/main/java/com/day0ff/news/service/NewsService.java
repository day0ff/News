package com.day0ff.news.service;

import com.day0ff.news.entity.News;

import java.util.List;

public interface NewsService {
    List<News> findAll();
    News findById(Long id);
    News fetchFindById(Long id);
    List<News> fetchNewsFindByPersonId(Long id);
}