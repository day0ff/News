package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Tags;
import com.day0ff.news.repository.TagsRepository;
import com.day0ff.news.service.TagsServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsImpl implements TagsServise {
    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public List<Tags> findAll() {
        return tagsRepository.findAll();
    }
}
