package com.day0ff.news.repository;

import com.day0ff.news.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonsRepository extends JpaRepository<Persons, Long> {
    @Query("SELECT p FROM Persons p JOIN FETCH p.user u WHERE p.id = ?1")
    Persons findById(Long id);

    @Query("SELECT p FROM Persons p JOIN FETCH p.user u WHERE u.userName = ?1")
    Persons findByUserName(String userName);

    @Query("SELECT p FROM Persons p JOIN FETCH p.user u WHERE u.userName = ?1 AND u.password =?2")
    Persons findByUserNameAndPassword(String userName, String password);
}
