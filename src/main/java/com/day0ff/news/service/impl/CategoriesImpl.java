package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Categories;
import com.day0ff.news.repository.CategoriesRepository;
import com.day0ff.news.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * The class implements methods of the business logic entities of entity Categories.
 */
@Service
public class CategoriesImpl implements CategoriesService {
    /**
     * property - set CategoriesRepository bean
     */
    @Autowired
    private CategoriesRepository categoriesRepository;
    /**
     * The method find all Categories objects in the database
     *
     * @return categories objects list
     */
    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }
    /**
     * The method find Category in the database by id
     *
     * @return Category object
     */
    @Override
    public Categories findById(Long id) {
        return categoriesRepository.findById(id);
    }

}
