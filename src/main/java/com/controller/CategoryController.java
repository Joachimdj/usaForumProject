/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Category;
 
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
@ManagedBean(name = "category")
@SessionScoped
public class CategoryController {
 private DataQuery query = new DataQuery();
    /**
     * Creates a new instance of CategoryController
     */
    public CategoryController() {
    }
       public List<Category> getCategoryList() throws SQLException
   {
       int forumId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("forumId"));
        
       return query.getCategoryList(forumId);
        
    }
}
