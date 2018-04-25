package com.day0ff.news.service;

import com.day0ff.news.entity.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAll();
    Categories findById(Long id);
/*    Categories save(Long id);
    void delete(Long id);*/
}
