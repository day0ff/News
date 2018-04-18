package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Comments;
import com.day0ff.news.repository.CommentsRepository;
import com.day0ff.news.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsImpl implements CommentsService{
    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public int getCountPersonComments(Long id) {
        return commentsRepository.getCountPersonComments(id);
    }

    @Override
    public int getCountNewsComments(Long id) {
        return commentsRepository.getCountNewsComments(id);
    }
}
