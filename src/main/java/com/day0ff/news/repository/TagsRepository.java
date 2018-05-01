package com.day0ff.news.repository;

import com.day0ff.news.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tags,Long> {
    Tags findById(Long id);
}
