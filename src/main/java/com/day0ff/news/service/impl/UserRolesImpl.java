package com.day0ff.news.service.impl;

import com.day0ff.news.entity.UserRoles;
import com.day0ff.news.entity.Users;
import com.day0ff.news.repository.UserRolesRepository;
import com.day0ff.news.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesImpl implements UserRolesService{
    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public List<UserRoles> findAll() {
        return userRolesRepository.findAll();
    }

    @Override
    public List<UserRoles> findByUserName(String userName){
        return userRolesRepository.findByUsersUserName(userName);
    }
}
