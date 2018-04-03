package com.day0ff.news.service;

import com.day0ff.news.entity.Users;

import java.util.List;

public interface UsersService {
    Users findByNameAndPassword(String userName, String password);
    List<Users> findAll();
}
