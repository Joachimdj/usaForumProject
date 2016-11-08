/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import com.beans.SessionBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Matti
 */
@ManagedBean
@SessionScoped
public class UserSession {

    private boolean loggedIn;

    public int getId() {
        return SessionBean.getUserId();
    }

    public String getName() {
        return SessionBean.getUserName();
    }

    public String getEmail() {
        return SessionBean.getUserEmail();
    }

    public int getPermissions() {
        return SessionBean.getUserPermissions();
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
