package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Categories;
import com.day0ff.news.repository.CategoriesRepository;
import com.day0ff.news.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories findById(Long id) {
        return categoriesRepository.findById(id);
    }

}
