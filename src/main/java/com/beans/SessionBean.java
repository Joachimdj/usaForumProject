package com.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class SessionBean {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static int getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (int) session.getAttribute("id");
        } else {
            return 0;
        }
    }

    public static String getUserName() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("name");
        } else {
            return "Username not found!";
        }
    }

    public static String getUserEmail() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("email");
        } else {
            return "Email not found!";
        }
    }

    public static int getUserPermissions() {
        HttpSession session = getSession();
        if (session != null) {
            return (int) Integer.valueOf((String) session.getAttribute("permissions"));
        } else {
            return 0;
        }
    
    }
    
}
