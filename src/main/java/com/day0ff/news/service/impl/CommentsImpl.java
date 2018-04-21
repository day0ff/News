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
    public Comments findById(Long id) {
        return commentsRepository.findById(id);
    }

    @Override
    public List<Comments> findCommentsByPerson_Id(Long id) {
        return commentsRepository.findCommentsByPerson_Id(id);
    }

    @Override
    public List<Comments> findCommentsByNews_Id(Long id) {
        return commentsRepository.findCommentsByNews_Id(id);
    }

    @Override
    public List<Comments> getPersonComments(Long id) {
        return commentsRepository.getPersonComments(id);
    }

    @Override
    public List<Comments> getNewsComments(Long id) {
        return commentsRepository.getNewsComments(id);
    }

    @Override
    public int getCountPersonComments(Long id) {
        return commentsRepository.getCountPersonComments(id);
    }

    @Override
    public int getCountNewsComments(Long id) {
        return commentsRepository.getCountNewsComments(id);
    }

    @Override
    public Comments save(Comments comment) {
        return commentsRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentsRepository.delete(id);
    }
}
