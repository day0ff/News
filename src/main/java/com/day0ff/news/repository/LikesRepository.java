package com.day0ff.news.repository;

import com.day0ff.news.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query("SELECT l FROM Likes l WHERE l.news.id = ?1")
    List<Likes> findByNews(Long newsId);

    @Query("SELECT l FROM Likes l WHERE l.news.id = ?1 AND l.person.id = ?2")
    Likes findByNewsAndPerson(Long newsId, Long personId);

    @Query("SELECT COUNT(l.news.id) FROM Likes l WHERE l.news.id = ?1")
    int getCountNewsLikes(Long id);

    @Query("SELECT COUNT(l.person.id) FROM Likes l WHERE l.person.id = ?1")
    int getCountPersonLikes(Long id);

    @Query("SELECT COUNT(l.person.id) FROM Likes l WHERE l.person.id = ?2 AND l.news.id = ?1")
    int isPersonLikeNews(Long newsId, Long personId);


}
