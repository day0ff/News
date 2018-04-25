package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Likes;
import com.day0ff.news.repository.LikesRepository;
import com.day0ff.news.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesImpl implements LikesService{
    @Autowired
    private LikesRepository likesRepository;

    @Override
    public Likes save(Likes likes) {
        return likesRepository.save(likes);
    }

    @Override
    public void delete(Likes likes) {
        likesRepository.delete(likes);
    }

    @Override
    public List<Likes> findAll() {
        return likesRepository.findAll();
    }

    @Override
    public List<Likes> findByNews(Long newsId) {
        return likesRepository.findByNews(newsId);
    }

    @Override
    public Likes findByNewsAndPerson(Long newsId, Long personId) {
        return likesRepository.findByNewsAndPerson(newsId, personId);
    }

    @Override
    public int getCountNewsLikes(Long id) {
        return likesRepository.getCountNewsLikes(id);
    }

    @Override
    public int getCountPersonLikes(Long id) {
        return likesRepository.getCountPersonLikes(id);
    }

    @Override
    public int isPersonLikeNews(Long personId, Long newsId) {
        return likesRepository.isPersonLikeNews(personId, newsId);
    }

}
