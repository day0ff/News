package com.day0ff.news.service;

import com.day0ff.news.entity.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments> findAll();

    Comments findById(Long id);

    List<Comments> findCommentsByPersonId(Long id);

    List<Comments> findCommentsByNewsId(Long id);

    List<Comments> getPersonComments(Long id);

    List<Comments> getNewsComments(Long id);

    int getCountPersonComments(Long id);

    int getCountNewsComments(Long id);

    Comments save(Comments comment);

    void delete(Long id);
}
