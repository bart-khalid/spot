/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.service;

import com.myWork.demo.bean.Login;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface LoginService {
    
    public int seConnecter(String username, String password);
    public Login findByUsername(String username);
    public List<Login> findByNbrSpot(double nbrSpot);
    public int save(Login login);
    
}
