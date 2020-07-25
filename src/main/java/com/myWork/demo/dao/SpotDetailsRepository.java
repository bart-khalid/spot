/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.dao;

import com.myWork.demo.bean.SpotDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public interface SpotDetailsRepository extends JpaRepository<SpotDetails, Long>{
    public SpotDetails findByReference(String reference);
}
