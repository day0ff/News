package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Users;
import com.day0ff.news.repository.UsersRepository;
import com.day0ff.news.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * The class implements methods of the business logic entities of entity Users.
 */
@Service
public class UsersImpl implements UsersService {
    /**
     * property - set UsersRepository bean
     */
    @Autowired
    private UsersRepository usersRepository;
    /**
     * The method find all Users objects in the database
     *
     * @return Users objects list
     */
    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }
    /**
     * The method find User object by user name and password in the database and get ignored field Roles
     *
     * @return User objects
     */
    @Transactional(readOnly = true)
    @Override
    public Users findByNameAndPassword(String userName, String password) {
        return usersRepository.findByUserNameAndPassword(userName, password);
    }
    /**
     * The method find User object by user name in the database
     *
     * @return User objects
     */
    @Transactional(readOnly = true)
    @Override
    public Users findByName(String userName) {
        return usersRepository.findByUserName(userName);
    }
    /**
     * The method find User object by id in the database
     *
     * @return User objects
     */
    @Transactional(readOnly = true)
    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id);
    }
    /**
     * The method save User object in the database
     *
     * @return User object
     */
    @Override
    public Users save(Users user) {
        return usersRepository.saveAndFlush(user);
    }
    /**
     * The method delete User object from database
     */
    @Override
    public void delete(Users user) {
        usersRepository.delete(user);
    }
    /**
     * The method return Users count by user name on the News
     *
     * @return count of User
     */
    @Override
    public int getCountUserByUserName(String userName) {
        return usersRepository.getCountUserByUserName(userName);
    }
}
