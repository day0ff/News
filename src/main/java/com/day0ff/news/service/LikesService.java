package com.day0ff.news.service;

import com.day0ff.news.entity.Likes;

import java.util.List;

public interface LikesService {
    Likes save(Likes likes);
    void delete(Likes likes);
    List<Likes> findAll();
    List<Likes> findByNews(Long newsId);
    Likes findByNewsAndPerson(Long newsId, Long personId);
    int getCountNewsLikes(Long id);
    int getCountPersonLikes(Long id);
    int isPersonLikeNews(Long personId, Long newsId);
}
