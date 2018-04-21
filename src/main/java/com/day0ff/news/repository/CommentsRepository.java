package com.day0ff.news.repository;

import com.day0ff.news.entity.Comments;
import com.day0ff.news.entity.News;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
//    @Query("SELECT c FROM Comments c JOIN FETCH c.news n JOIN FETCH c.person p WHERE c.id = ?1")
    Comments findById(Long id);

    List<Comments> findCommentsByPerson_Id(Long id);
    List<Comments> findCommentsByNews_Id(Long id);

    @Query("SELECT c FROM Comments c WHERE c.person.id = ?1")
    List<Comments> getPersonComments(Long id);

    @Query("SELECT c FROM Comments c WHERE c.news.id = ?1")
    List<Comments> getNewsComments(Long id);

    @Query("SELECT COUNT(c.person.id) FROM Comments c WHERE c.person.id = ?1")
    int getCountPersonComments(Long id);

    @Query("SELECT COUNT(c.news.id) FROM Comments c WHERE c.news.id = ?1")
    int getCountNewsComments(Long id);

}
