package com.day0ff.news.repository;

import com.day0ff.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findById(Long id);

    @Query("SELECT n FROM News n JOIN FETCH n.tags t WHERE n.id = ?1")
    News fetchNewsFindById(Long id);
}
