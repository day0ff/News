package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Likes;
import com.day0ff.news.repository.LikesRepository;
import com.day0ff.news.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * The class implements methods of the business logic entities of entity Likes.
 */
@Service
public class LikesImpl implements LikesService{
    /**
     * property - set LikesRepository bean
     */
    @Autowired
    private LikesRepository likesRepository;
    /**
     * The method save Like in the database
     *
     * @return Like object
     */
    @Override
    public Likes save(Likes likes) {
        return likesRepository.save(likes);
    }
    /**
     * The method delete Like object from database
     */
    @Override
    public void delete(Likes likes) {
        likesRepository.delete(likes);
    }
    /**
     * The method find all Likes objects in the database
     *
     * @return Likes objects list
     */
    @Override
    public List<Likes> findAll() {
        return likesRepository.findAll();
    }
    /**
     * The method find all Likes objects by News id in the database
     *
     * @return Likes objects list
     */
    @Override
    public List<Likes> findByNews(Long newsId) {
        return likesRepository.findByNews(newsId);
    }
    /**
     * The method find all Likes objects by news Person in the database
     *
     * @return Likes objects list
     */
    @Override
    public List<Likes> findByPersonId(Long personId) {
        return likesRepository.findByPersonId(personId);
    }
    /**
     * The method return Person Likes on the News
     *
     * @return count of Likes
     */
    @Override
    public int isPersonLikeNews(Long personId, Long newsId) {
        return likesRepository.isPersonLikeNews(personId, newsId);
    }
    /**
     * The method find all Like by News id and Person id in the database
     *
     * @return Like object
     */
    @Override
    public Likes findByNewsAndPerson(Long newsId, Long personId) {
        return likesRepository.findByNewsAndPerson(newsId, personId);
    }
    /**
     * The method return News Likes count from database
     *
     * @return Likes count
     */
    @Override
    public int getCountNewsLikes(Long id) {
        return likesRepository.getCountNewsLikes(id);
    }
    /**
     * The method return Person Likes count from database
     *
     * @return Likes count
     */
    @Override
    public int getCountPersonLikes(Long id) {
        return likesRepository.getCountPersonLikes(id);
    }

}
