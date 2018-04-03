package com.day0ff.news.repository;

import com.day0ff.news.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {
    Users findByUserNameAndPassword(String userName, String password);
}
