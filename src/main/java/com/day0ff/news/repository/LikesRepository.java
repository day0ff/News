package com.day0ff.news.repository;

import com.day0ff.news.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query("SELECT COUNT(l.news.id) FROM Likes l WHERE l.news.id = ?1")
    int getCountNewsLikes(Long id);

    @Query("SELECT COUNT(l.person.id) FROM Likes l WHERE l.person.id = ?1")
    int getCountPersonLikes(Long id);

    @Query("SELECT COUNT(l.person.id) FROM Likes l WHERE l.person.id = ?1 AND l.news.id = ?2")
    int isPersonLikeNews(Long personId, Long newsId);
}
