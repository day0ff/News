package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Roles;
import com.day0ff.news.repository.RolesRepository;
import com.day0ff.news.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * The class implements methods of the business logic entities of entity Roles.
 */
@Service
public class RolesImpl implements RolesService {
    /**
     * property - set RolesRepository bean
     */
    @Autowired
    private RolesRepository rolesRepository;
    /**
     * The method find all Roles objects in the database
     *
     * @return Roles objects list
     */
    @Override
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }
    /**
     * The method find Role object by id in the database
     *
     * @return Role objects
     */
    @Override
    public Roles findById(Long id){
        return rolesRepository.findById(id);
    }
}
