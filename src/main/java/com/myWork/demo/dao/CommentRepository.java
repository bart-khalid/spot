/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.dao;

import com.myWork.demo.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    public Comment findByReference (String reference);
}
