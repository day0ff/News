package com.day0ff.news.service;

import com.day0ff.news.entity.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments>findAll();
}
