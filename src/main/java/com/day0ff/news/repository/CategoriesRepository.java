package com.day0ff.news.repository;

import com.day0ff.news.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    Categories findById(Long id);
}
