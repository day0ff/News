package com.day0ff.news.service;

import com.day0ff.news.entity.Tags;

import java.util.List;

public interface TagsService {
    List<Tags> findAll();
    Tags findById(Long id);
    Tags save(Tags tag);
}
