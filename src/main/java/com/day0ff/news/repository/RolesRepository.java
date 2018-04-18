package com.day0ff.news.repository;

import com.day0ff.news.entity.News;
import com.day0ff.news.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository extends JpaRepository<Roles,String> {
    Roles findById(Long id);
}
