/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.dao;

import com.myWork.demo.bean.Login;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{
    public Login findByUsername(String username);
    public Login findByReference(String reference);
    public List<Login> findByNbrSpot(double nbrSpot);
}
