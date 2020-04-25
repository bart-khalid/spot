/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.service.impl;

import com.myWork.demo.bean.Login;
import com.myWork.demo.bean.Spot;
import com.myWork.demo.dao.LoginRepository;
import com.myWork.demo.dao.SpotRepository;
import com.myWork.demo.service.SpotService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Override
    public int save(Spot spot, String username) {
        spot.setUsername(username);
        Login loadedUser = loginRepository.findByUsername(username);
        loadedUser.setNbrSpot(loadedUser.getNbrSpot() + 1);
        spot.setLogin(loadedUser);
        spot.setDate(new Date());
        spot.setEmail(loadedUser.getEmail());
        spot.setNbrSpot(loadedUser.getNbrSpot());
        spotRepository.save(spot);
        return 1;
    }

    @Override
    public int deleteById(Long id) {
        Optional<Spot> foundedSpot = spotRepository.findById(id);
        if (foundedSpot == null) {
            return -1;
        } else {
            spotRepository.deleteById(id);
            return 1;
        }

    }

    @Override
    public Optional<Spot> findById(Long id) {
        Optional<Spot> foundedSpot = spotRepository.findById(id);
        return foundedSpot;
    }

    @Override
    public List<Spot> findAll() {
        return spotRepository.findAll();
    }

    @Override
    public int addLike(Spot spot, String username) {
        Login foundedUser = loginRepository.findByUsername(username);
        List<Spot> myLikedSpots = foundedUser.getMyLikedSpots();
        if (testerSiExist(spot, myLikedSpots) == 1) {
            return -1;
        } else {
            myLikedSpots.add(spot);
            foundedUser.setMyLikedSpots(myLikedSpots);
            loginRepository.save(foundedUser);
            spot.setNbrLike(spot.getNbrLike() + 1);
            spot.setUserLovedSpot(foundedUser);
            spotRepository.save(spot);
            return 1;
        }
       
    }
    
    public int testerSiExist(Spot spot, List<Spot> spots) {
        for (Spot spt : spots) {
            if (spt.equals(spot)) {
                return 1;
            }
        } 
        return -1;
    }
}
