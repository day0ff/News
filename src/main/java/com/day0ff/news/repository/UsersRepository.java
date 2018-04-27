package com.day0ff.news.repository;

import com.day0ff.news.entity.Roles;
import com.day0ff.news.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    @Query("SELECT u FROM Users u JOIN FETCH u.roles r WHERE u.userName = ?1 AND u.password =?2")
    Users findByUserNameAndPassword(String userName, String password);

    @Query("SELECT u FROM Users u JOIN FETCH u.roles r WHERE u.userName = ?1")
    Users findByUserName(String userName);

    @Query("SELECT u FROM Users u JOIN FETCH u.roles r WHERE u.id = ?1")
    Users findById(Long id);

    @Query("SELECT COUNT(u.id) FROM Users u WHERE u.userName = ?1")
    int getCountUserByUserName(String userName);
}
