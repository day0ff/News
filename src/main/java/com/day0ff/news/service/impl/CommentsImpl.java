package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Comments;
import com.day0ff.news.repository.CommentsRepository;
import com.day0ff.news.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * The class implements methods of the business logic entities of entity Comments.
 */
@Service
public class CommentsImpl implements CommentsService{
    /**
     * property - set CommentsRepository bean
     */
    @Autowired
    private CommentsRepository commentsRepository;
    /**
     * The method find all Comments objects in the database
     *
     * @return Comments objects list
     */
    @Override
    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }
    /**
     * The method find Comment in the database by id
     *
     * @return Comment object
     */
    @Override
    public Comments findById(Long id) {
        return commentsRepository.findById(id);
    }
    /**
     * The method find all Comments objects by person id in the database
     *
     * @return Comments objects list
     */
    @Override
    public List<Comments> findCommentsByPersonId(Long id) {
        return commentsRepository.findCommentsByPersonId(id);
    }
    /**
     * The method find all Comments objects by news id in the database
     *
     * @return Comments objects list
     */
    @Override
    public List<Comments> findCommentsByNewsId(Long id) {
        return commentsRepository.findCommentsByNewsId(id);
    }
    /**
     * The method find all Comments objects by person id in the database
     *
     * @return Comments objects list
     */
    @Override
    public List<Comments> getPersonComments(Long id) {
        return commentsRepository.getPersonComments(id);
    }
    /**
     * The method find all Comments objects by news id in the database
     *
     * @return Comment objects list
     */
    @Override
    public List<Comments> getNewsComments(Long id) {
        return commentsRepository.getNewsComments(id);
    }
    /**
     * The method return Person Comments count from database
     *
     * @return Comments count
     */
    @Override
    public int getCountPersonComments(Long id) {
        return commentsRepository.getCountPersonComments(id);
    }
    /**
     * The method return News Comments count from database
     *
     * @return Comments count
     */
    @Override
    public int getCountNewsComments(Long id) {
        return commentsRepository.getCountNewsComments(id);
    }
    /**
     * The method save Comment in the database
     *
     * @return Comment object
     */
    @Override
    public Comments save(Comments comment) {
        return commentsRepository.save(comment);
    }
    /**
     * The method delete Comment object from database
     */
    @Override
    public void delete(Long id) {
        commentsRepository.delete(id);
    }
}
