package com.day0ff.news.service;

import com.day0ff.news.entity.Likes;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikesService {
    List<Likes> findAll();
    int getCountNewsLikes(Long id);
    int getCountPersonLikes(Long id);
    int isPersonLikeNews(Long personId, Long newsId);
}
