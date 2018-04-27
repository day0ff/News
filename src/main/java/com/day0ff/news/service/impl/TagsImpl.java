package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Tags;
import com.day0ff.news.repository.TagsRepository;
import com.day0ff.news.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsImpl implements TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public List<Tags> findAll() {
        return tagsRepository.findAll();
    }

    @Override
    public Tags findById(Long id) {
        return tagsRepository.findById(id);
    }

    @Override
    public Tags save(Tags tag) {
        return tagsRepository.saveAndFlush(tag);
    }
}
