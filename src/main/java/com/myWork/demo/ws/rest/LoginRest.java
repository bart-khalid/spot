/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.ws.rest;

import com.myWork.demo.bean.Login;
import com.myWork.demo.service.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("spot/login")
public class LoginRest {
    
    @Autowired
    LoginService loginService;
    
    
    @GetMapping("/connect/{username}/{password}")
    public int seConnecter(@PathVariable String username,@PathVariable String password){
        return loginService.seConnecter(username, password);
    }
    
    @GetMapping("/user/{username}")
    public Login findByUsername(@PathVariable String username){
        return loginService.findByUsername(username);
    }
    
    @GetMapping("/getUsers")
    public List<Login> findByNbrSpot(@PathVariable double nbrSpot){
        return loginService.findByNbrSpot(nbrSpot);
    }
    
    @PostMapping("/")
    public int save(@RequestBody Login login) {
        return loginService.save(login);
    }
}
