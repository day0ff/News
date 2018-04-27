package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Users;
import com.day0ff.news.repository.UsersRepository;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Users findByNameAndPassword(String userName, String password) {
        return usersRepository.findByUserNameAndPassword(userName, password);
    }

    @Transactional(readOnly = true)
    @Override
    public Users findByName(String userName) {
        return usersRepository.findByUserName(userName);
    }

    @Transactional(readOnly = true)
    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users save(Users user) {
        return usersRepository.saveAndFlush(user);
    }

    @Override
    public void delete(Users user) {
        usersRepository.delete(user);
    }

    @Override
    public int getCountUserByUserName(String userName) {
        return usersRepository.getCountUserByUserName(userName);
    }
}
