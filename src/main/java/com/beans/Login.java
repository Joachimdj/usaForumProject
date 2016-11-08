package com.beans;

import com.utils.LoginDAO;
import com.utils.UserSession;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String user;
    private String pwd;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    //validate login
    public String validateUsernamePassword() {

        FacesContext context = FacesContext.getCurrentInstance();
        UserSession us = (UserSession) context.getApplication()
                .evaluateExpressionGet(context, "#{userSession}", UserSession.class);

        boolean valid = LoginDAO.validate(user, pwd);
        if (valid) {

            us.setLoggedIn(true);

            //This part just prints out the users session
            //information right after login has occurred.
            int saId = SessionBean.getUserId();
            System.out.println("Session ID: " + saId);

            String saName = SessionBean.getUserName();
            System.out.println("Session Name: " + saName);

            String saEmail = SessionBean.getUserEmail();
            System.out.println("Session Email: " + saEmail);

            int saPermissions = SessionBean.getUserPermissions();
            System.out.println("Session Permissions: " + saPermissions);

            return "forum";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "index";
        }
    }

    //logout event, invalidate session
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession us = (UserSession) context.getApplication()
                .evaluateExpressionGet(context, "#{userSession}", UserSession.class);
        
        us.setLoggedIn(false);

        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "index";
    }
}
