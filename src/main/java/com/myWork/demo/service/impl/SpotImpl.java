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
import com.myWork.demo.dao.LoginRepository;
import com.myWork.demo.dao.SpotDetailsRepository;
import com.myWork.demo.dao.SpotRepository;
import com.myWork.demo.service.CommentService;
import com.myWork.demo.service.SpotService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class SpotImpl implements SpotService {

    @Autowired
    SpotRepository spotRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    SpotDetailsRepository spotDetailsRepository;
    
    @Autowired
    CommentService commentService;

    @Override
    public int save(Spot spot, String username) {

        Login loadedUser = loginRepository.findByUsername(username);
        if (loadedUser == null) {
            return -1;
        } else {
            spot.setUsername(username);
            loadedUser.setNbrSpot(loadedUser.getNbrSpot() + 1);
            spot.setDate(new Date());
            spot.setEmail(loadedUser.getEmail());
            spot.setLogin(loadedUser);
            List<Spot> mySpots = loadedUser.getMySpots();
            mySpots.add(spot);
            loadedUser.setMySpots(mySpots);
            loginRepository.save(loadedUser);
            spotRepository.save(spot);
            // set reference
            spot.setReference("Spot" + String.valueOf(spot.getId()));
            spotRepository.save(spot);
            return 1;
        }

    }

    @Override
    public int deleteByReference(String reference) {
        Spot foundedSpot = spotRepository.findByReference(reference);
        if (foundedSpot == null) {
            return -1;
        } else {
            // remove spot from login spots list
            Login login = foundedSpot.getLogin();
            List<Spot> mySpots = login.getMySpots();
            mySpots.remove(foundedSpot);
            login.setMySpots(mySpots);
            loginRepository.save(login);
            // delete comments
            List<Comment> comments = foundedSpot.getComments();
            for (Comment comment : comments) {
               commentService.deleteByReference(comment.getReference());
            }
            // delete spotDetails
            List<SpotDetails> spotsDetails = foundedSpot.getSpots();
            for (SpotDetails spotsDetail : spotsDetails) {
                spotDetailsRepository.delete(spotsDetail);
            }
            spotRepository.delete(foundedSpot);
            return 1;
        }

    }

    @Override
    public List<Spot> findAll() {
        return spotRepository.findAll();
    }

    @Override
    public int addLike(Spot spot, String username) {
        Login foundedUser = loginRepository.findByUsername(username);
        SpotDetails foundedSpotLikedByThisUser = spotDetailsRepository.findByReference(spot.getReference() + username);

        if (foundedUser == null) {
            return -1;
        } else if (foundedSpotLikedByThisUser != null) {
            return -2;
        } else {
            Spot foundedSpot = spotRepository.findByReference(spot.getReference());
            foundedSpot.setNbrLike(foundedSpot.getNbrLike() + 1);
            spotRepository.save(foundedSpot);
            SpotDetails spotD = new SpotDetails();
            spotD.setReference(foundedSpot.getReference() + username);
            spotD.setLogin(foundedUser);
            spotD.setSpot(foundedSpot);
            spotDetailsRepository.save(spotD);
            return 1;
        }
    }
    
    @Override
    public Spot findByReference(String reference) {
        Spot foundedSpot = spotRepository.findByReference(reference);
        return foundedSpot;
    }

    @Override
    public List<Spot> findByUserName(String username) {
        Login foundedUser = loginRepository.findByUsername(username);
        if (foundedUser != null) {
           return foundedUser.getMySpots(); 
        } else {
            return null;
        }
        
    }
}
