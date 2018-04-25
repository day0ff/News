package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Persons;
import com.day0ff.news.repository.PersonsRepository;
import com.day0ff.news.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsImpl implements PersonsService {
    @Autowired
    private PersonsRepository personsRepository;

    @Override
    public Persons findById(Long id) {
        return personsRepository.findById(id);
    }

    @Override
    public List<Persons> findAll() {
        return personsRepository.findAll();
    }

    @Override
    public Persons findByName(String name) {
        return personsRepository.findByUserName(name);
    }

    @Override
    public Persons findByNameAndPassword(String userName, String password) {
        return personsRepository.findByUserNameAndPassword(userName, password);
    }

    @Override
    public Persons save(Persons person) {
        return personsRepository.saveAndFlush(person);
    }

    @Override
    public void delete(Long id) {
        personsRepository.delete(id);
    }
}
