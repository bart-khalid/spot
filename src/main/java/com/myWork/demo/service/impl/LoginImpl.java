/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.service.impl;

import com.myWork.demo.bean.Login;
import com.myWork.demo.service.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myWork.demo.dao.LoginRepository;

/**
 *
 * @author lenovo
 */
@Service
public class LoginImpl implements LoginService {

    @Autowired
    LoginRepository loginRepository;

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
            return 1;
        }
    }

}
