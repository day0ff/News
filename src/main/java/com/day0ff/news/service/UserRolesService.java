package com.day0ff.news.service;

import com.day0ff.news.entity.UserRoles;

import java.util.List;

public interface UserRolesService {
    List<UserRoles> findByUserName(String userName);
    List<UserRoles> findAll();
}
