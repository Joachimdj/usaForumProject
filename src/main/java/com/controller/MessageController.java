/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Message;
import com.query.DataQuery;
import com.utils.Hasher;
import com.utils.UserSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Matti
 */
@ManagedBean(name = "message")
@SessionScoped
public class MessageController implements Serializable {

    private String name;
    private String message;
    private int subjectId;
    private DataQuery query = new DataQuery();

    public String createMessage() {
        //subjectId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("subjectId"));
        //System.out.println("This is the subjectID: " + subjectId);
         UserSession us = new UserSession();
         String username = us.getName();
         
         System.out.println("USERNAME: " + username);
         System.out.println("MESSAGE: " + message);
         
        if (query.createMessage(username, message, subjectId)) {
            return "subject.xhtml?subjectId="+subjectId+"&faces-redirect=true";
        }
        return "";
    }

    public List<Message> getMessageList() throws SQLException {
        try {
            subjectId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("subjectId"));
        } catch (Exception e) {
            System.out.println("THIS IS THE ERROR");
        }
        //name = query.getSubject(subjectId).getName();
        //message = query.getSubject(subjectId).getSubjectDesc();

        return query.getMessageList(subjectId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataQuery getQuery() {
        return query;
    }

    public void setQuery(DataQuery query) {
        this.query = query;
    }

}
