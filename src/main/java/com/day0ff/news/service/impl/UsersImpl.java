package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Users;
import com.day0ff.news.repository.UsersRepository;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersImpl implements UsersService{
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Users findByNameAndPassword(String userName, String password){
        return usersRepository.findByUserNameAndPassword(userName, password);
    }
}
