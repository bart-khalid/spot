/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.service.impl;

import com.myWork.demo.bean.Comment;
import com.myWork.demo.bean.Login;
import com.myWork.demo.bean.Spot;
import com.myWork.demo.dao.CommentRepository;
import com.myWork.demo.dao.LoginRepository;
import com.myWork.demo.dao.SpotRepository;
import com.myWork.demo.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class CommentImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    SpotRepository spotRepository;



    @Override
    public int deleteByReference(String reference) {
        Comment foundedComment = commentRepository.findByReference(reference);
        Spot loadedSpot = spotRepository.findByReference(foundedComment.getSpot().getReference());
        loadedSpot.setNbrComments(loadedSpot.getNbrComments() - 1);
        commentRepository.delete(foundedComment);
        return 1;
    }

    @Override
    public int save(Comment comment, String reference, String username) {
        Login foundedUser = loginRepository.findByUsername(username);
        if (foundedUser == null) {
            return -1;
        } else {
            Spot foundedSpot = spotRepository.findByReference(reference);
            comment.setLogin(foundedUser);
            comment.setSpot(foundedSpot);
            comment.setUsername(foundedUser.getUsername());
            commentRepository.save(comment);
            comment.setReference("Comment"+String.valueOf(comment.getId()));
            commentRepository.save(comment);
            // update list comment of foundedSpot
            foundedSpot.setNbrComments(foundedSpot.getNbrComments() + 1);
            List<Comment> comments = foundedSpot.getComments();
            comments.add(comment);
            foundedSpot.setComments(comments);
            spotRepository.save(foundedSpot);
            // update list comments of foundedUser
            List<Comment> comments1 = foundedUser.getComments();
            comments1.add(comment);
            foundedUser.setComments(comments1);
            loginRepository.save(foundedUser);
            return 1;
        }
    }
    
}
