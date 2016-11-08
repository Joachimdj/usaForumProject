/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Category;
import com.entity.Subject;
import com.query.DataQuery;
import java.sql.SQLException;
import java.util.List; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joachimdittman
 */
@ManagedBean(name = "subjects")
@SessionScoped
public class SubjectController {
    
private String name;
    private String desc;
    private int subjectId;
private DataQuery query = new DataQuery();

    /**
     * Creates a new instance of SubjectController
     */
    public SubjectController() {
    }
         public List<Subject> getSubjectList() throws SQLException
   {
       int catId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoryId"));
        
       return query.getSubjectList(catId);
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public DataQuery getQuery() {
        return query;
    }

    public void setQuery(DataQuery query) {
        this.query = query;
    }
          
}
