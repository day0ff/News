package com.day0ff.news.repository;

import com.day0ff.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findById(Long id);

    @Query("SELECT n FROM News n JOIN FETCH n.tags t WHERE n.id = ?1")
    News fetchNewsFindById(Long id);

    @Query("SELECT n FROM News n JOIN FETCH n.tags t WHERE t.id = ?1")
    List<News> fetchNewsFindByTagId(Long id);

    @Query("SELECT n FROM News n JOIN FETCH n.tags t WHERE t.tag = ?1")
    List<News> getNewsByTagTitle(String title);

    @Query("SELECT n FROM News n JOIN FETCH n.person p WHERE p.id = ?1")
    List<News> fetchNewsFindByPersonId(Long id);

    @Query("SELECT COUNT(n.person.id) FROM News n WHERE n.person.id = ?1")
    int fetchNewsCountFindByPersonId(Long id);
}
