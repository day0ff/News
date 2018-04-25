package com.day0ff.news.service.impl;

import com.day0ff.news.entity.Roles;
import com.day0ff.news.repository.RolesRepository;
import com.day0ff.news.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesImpl implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Roles findById(Long id){
        return rolesRepository.findById(id);
    }
}
