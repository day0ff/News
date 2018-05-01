package com.day0ff.news.service;

import com.day0ff.news.entity.Roles;

import java.util.List;

public interface RolesService {
    List<Roles> findAll();

    Roles findById(Long id);
}
