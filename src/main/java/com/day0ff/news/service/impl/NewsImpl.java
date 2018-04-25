package com.day0ff.news.service.impl;

import com.day0ff.news.entity.News;
import com.day0ff.news.repository.NewsRepository;
import com.day0ff.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News save(News news) {
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public News update(News news) {
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public void delete(Long id) {
        newsRepository.delete(id);
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public News fetchFindById(Long id) {
        return newsRepository.fetchNewsFindById(id);
    }

    @Override
    public News fetchNewsCategoriesFindById(Long id) {
        return newsRepository.fetchNewsCategoriesFindById(id);
    }

    @Override
    public List<News> fetchNewsFindByPersonId(Long id) {
        return newsRepository.fetchNewsFindByPersonId(id);
    }

    @Override
    public int fetchNewsCountFindByPersonId(Long id) {
        return newsRepository.fetchNewsCountFindByPersonId(id);
    }

    @Override
    public List<News> findNewsByCategories(String category) {
        return newsRepository.findNewsByCategories(category);
    }

    @Override
    public List<News> getNewsFindByTagId(Long id) {
        return newsRepository.fetchNewsFindByTagId(id);
    }

    @Override
    public List<News> getNewsFindByTagTitle(String title) {
        return newsRepository.getNewsByTagTitle(title);
    }

    @Transactional(readOnly = true)
    @Override
    public void incrementNewsViews(Long newsId) {
        newsRepository.incrementNewsViews(newsId);
    }
}
