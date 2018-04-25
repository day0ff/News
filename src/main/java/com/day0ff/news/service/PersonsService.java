package com.day0ff.news.service;

import com.day0ff.news.entity.Persons;

import java.util.List;

public interface PersonsService {
    Persons findById(Long id);

    List<Persons> findAll();

    Persons findByName(String name);

    Persons findByNameAndPassword(String userName, String password);

    Persons save(Persons person);

    void delete(Long id);
}
