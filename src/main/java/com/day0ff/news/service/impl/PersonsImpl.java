package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Persons;
import com.day0ff.news.repository.PersonsRepository;
import com.day0ff.news.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * The class implements methods of the business logic entities of entity Persons.
 */
@Service
public class PersonsImpl implements PersonsService {
    /**
     * property - set PersonsRepository bean
     */
    @Autowired
    private PersonsRepository personsRepository;
    /**
     * The method find Person object by id in the database
     *
     * @return Person objects
     */
    @Override
    public Persons findById(Long id) {
        return personsRepository.findById(id);
    }
    /**
     * The method find all Persons objects in the database
     *
     * @return Persons objects list
     */
    @Override
    public List<Persons> findAll() {
        return personsRepository.findAll();
    }
    /**
     * The method find Person object by name in the database
     *
     * @return Person objects
     */
    @Override
    public Persons findByName(String name) {
        return personsRepository.findByUserName(name);
    }
    /**
     * The method find Person object by name and password in the database
     *
     * @return Person objects
     */
    @Override
    public Persons findByNameAndPassword(String userName, String password) {
        return personsRepository.findByUserNameAndPassword(userName, password);
    }
    /**
     * The method save Person object in the database
     *
     * @return Person object
     */
    @Override
    public Persons save(Persons person) {
        return personsRepository.saveAndFlush(person);
    }
    /**
     * The method delete Person object from database by id
     */
    @Override
    public void delete(Long id) {
        personsRepository.delete(id);
    }
}
