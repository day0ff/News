package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Tags;
import com.day0ff.news.repository.TagsRepository;
import com.day0ff.news.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * The class implements methods of the business logic entities of entity Tags.
 */
@Service
public class TagsImpl implements TagsService {
    /**
     * property - set TagsRepository bean
     */
    @Autowired
    private TagsRepository tagsRepository;
    /**
     * The method find all Tags objects in the database
     *
     * @return Tags objects list
     */
    @Override
    public List<Tags> findAll() {
        return tagsRepository.findAll();
    }
    /**
     * The method find Tag object by id in the database
     *
     * @return Tag objects
     */
    @Override
    public Tags findById(Long id) {
        return tagsRepository.findById(id);
    }
    /**
     * The method save Tag object in the database
     *
     * @return Tag object
     */
    @Override
    public Tags save(Tags tag) {
        return tagsRepository.saveAndFlush(tag);
    }
}
