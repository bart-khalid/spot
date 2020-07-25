/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.service.impl;

import com.myWork.demo.bean.Comment;
import com.myWork.demo.bean.Login;
import com.myWork.demo.bean.Spot;
import com.myWork.demo.bean.SpotDetails;
import com.myWork.demo.dao.CommentRepository;
import com.myWork.demo.service.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myWork.demo.dao.LoginRepository;
import com.myWork.demo.dao.SpotDetailsRepository;
import com.myWork.demo.dao.SpotRepository;

/**
 *
 * @author lenovo
 */
@Service
public class LoginImpl implements LoginService {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    SpotDetailsRepository spotDetailsRepository;
    @Autowired
    SpotRepository spotRepository;

    @Override
    public int seConnecter(String username, String password) {
        Login foundedLogin = loginRepository.findByUsername(username);
        if (foundedLogin == null) {
            return -1;
        } else if (!foundedLogin.getPassword().equals(password)) {
            return -2;
        } else {
            return 1;
        }

    }

    @Override
    public Login findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    @Override
    public List<Login> findByNbrSpot(double nbrSpot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(Login login) {
        Login foundedUser = loginRepository.findByUsername(login.getUsername());
        if (foundedUser != null) {
            return -1;
        } else if (login.getPassword() == null) {
            return -2;
        } else if (login.getEmail() == null) {
            return -3;
        } else {
            loginRepository.save(login);
            login.setReference("User" + String.valueOf(login.getId()));
            loginRepository.save(login);
            return 1;
        }
    }

    @Override
    public int update(Login login) {
        Login foundedLogin = loginRepository.findByUsername(login.getUsername());
        if (foundedLogin != null && foundedLogin.getReference().equals(login.getReference())) {
            foundedLogin.setEmail(login.getEmail());
            foundedLogin.setPassword(login.getPassword());
            loginRepository.save(foundedLogin);
            List<Comment> comments = foundedLogin.getComments();
            for (Comment comment : comments) {
                comment.setUsername(foundedLogin.getUsername());
                commentRepository.save(comment);
            }
            List<Spot> mySpots = foundedLogin.getMySpots();
            for (Spot mySpot : mySpots) {
                mySpot.setUsername(foundedLogin.getUsername());
                spotRepository.save(mySpot);
            }
            List<SpotDetails> mySpots1 = foundedLogin.getMyLikedSpots();
            for (SpotDetails spot : mySpots1) {    
                spot.setReference(spot.getSpot().getReference()+foundedLogin.getUsername());
                spotDetailsRepository.save(spot);
            }
            return 1;
        } else if (foundedLogin != null && !foundedLogin.getReference().equals(login.getReference())) {
            System.out.println("Username Duplicated");
             return -1;
        } else {
            Login foundedLoginByRef = loginRepository.findByReference(login.getReference());
            foundedLoginByRef.setUsername(login.getUsername());
            foundedLoginByRef.setEmail(login.getEmail());
            foundedLoginByRef.setPassword(login.getPassword());
            loginRepository.save(foundedLoginByRef);
            List<Comment> comments = foundedLoginByRef.getComments();
            for (Comment comment : comments) {
                comment.setUsername(foundedLoginByRef.getUsername());
                commentRepository.save(comment);
            }
            List<Spot> mySpots = foundedLoginByRef.getMySpots();
            for (Spot mySpot : mySpots) {
                mySpot.setUsername(foundedLoginByRef.getUsername());
                spotRepository.save(mySpot);
            }
            List<SpotDetails> mySpots1 = foundedLoginByRef.getMyLikedSpots();
            for (SpotDetails spot : mySpots1) {    
                spot.setReference(spot.getSpot().getReference()+foundedLoginByRef.getUsername());
                spotDetailsRepository.save(spot);
            }
            return 2;
        }
    }

    @Override
    public Login findByReference(String reference) {
        return loginRepository.findByReference(reference);
    }

}
