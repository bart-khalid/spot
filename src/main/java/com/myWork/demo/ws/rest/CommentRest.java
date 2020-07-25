/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.ws.rest;

import com.myWork.demo.bean.Comment;
import com.myWork.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("spot/Comment")
public class CommentRest {
    @Autowired
    CommentService commentService;

    @PostMapping("/{reference}/{username}")
    public int save(@RequestBody Comment comment,@PathVariable String reference,@PathVariable String username) {
        return commentService.save(comment, reference, username);
    }

    @DeleteMapping("/delete/reference/{reference}")
    public int deleteByReference(@PathVariable String reference) {
        return commentService.deleteByReference(reference);
    }
}
