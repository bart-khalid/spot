/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.service;

import com.myWork.demo.bean.Login;
import com.myWork.demo.bean.Spot;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author lenovo
 */
public interface SpotService {

    public int save(Spot spot, String username);

    public int deleteByReference(String reference);

    public Spot findByReference(String reference);
    
    public List<Spot> findAll();
    
    public int addLike(Spot spot, String username);
    
    public List<Spot> findByUserName(String username);
}
