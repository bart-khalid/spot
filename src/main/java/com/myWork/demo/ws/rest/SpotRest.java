/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.ws.rest;

import com.myWork.demo.bean.Login;
import com.myWork.demo.bean.Spot;
import com.myWork.demo.service.SpotService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = {"https://spotteduv.herokuapp.com"})
@RequestMapping("spot")
public class SpotRest {
    
    @Autowired
    SpotService spotService;
    
    @PostMapping("/{username}")
    public int save(@RequestBody Spot spot,@PathVariable String username){
        return spotService.save(spot, username);
    }
    
    
    @GetMapping("/find/{reference}")
    public Spot findByReference(@PathVariable String reference){
        return spotService.findByReference(reference);
    }
    
    @GetMapping("/")
    public List<Spot> findAll() {
        return spotService.findAll();
    }
    
    
    @PutMapping("/addLike/{username}")
    public int addLike(@RequestBody Spot spot,@PathVariable String username) {

        return spotService.addLike(spot, username);
    }

    @DeleteMapping("delete/{reference}")
    public int deleteByReference(@PathVariable String reference) {
        return spotService.deleteByReference(reference);
    }

    @GetMapping("/mySpots/{username}")
    public List<Spot> findByUserName(@PathVariable String username) {
        return spotService.findByUserName(username);
    }
    
}
