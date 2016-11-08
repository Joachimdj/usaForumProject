/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.query.DataQuery;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Matti
 */
@ManagedBean(name = "signup")
@SessionScoped
public class SignupController implements Serializable{
    
    private String name;
    private String username;
    private String email;
    private String password;
    private DataQuery query = new DataQuery();
    
    public String createUser() {
        
        if (query.SignupControl(name, email, password)) {
            return "index.xhtml?faces-redirect=true";
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
