/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.service;

import com.myWork.demo.bean.Comment;

/**
 *
 * @author lenovo
 */

public interface CommentService {
    public int save(Comment comment, String reference, String username);
    public int deleteByReference(String reference);
}
