package com.day0ff.news.controller;

import com.day0ff.news.entity.Comments;
import com.day0ff.news.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/editor")
public class EditorController {
    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestParam("id") Long commentId) {
        commentsService.delete(commentId);
    }

/*    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestParam("id") Long commentId) {
        Comments comment = commentsService.findById(commentId);
        commentsService.delete(comment);
    }*/
}
