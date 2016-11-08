package com.utils;

import com.beans.SessionBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

public class LoginDAO {

    public static boolean validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        
        //Encrypts password before checking the database
        String encrypted = Hasher.generateHash(password);

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM User WHERE email = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, encrypted);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = SessionBean.getSession();
                session.setAttribute("id", rs.getInt("id"));
                //System.out.println("LOOK AT THIS: " + rs.getInt("id"));
                
                session.setAttribute("name", rs.getString("name"));
                //System.out.println("LOOK AT THIS: " + rs.getString("name"));
                
                session.setAttribute("email", rs.getString("email"));
                //System.out.println("LOOK AT THIS: " + rs.getString("email"));
                
                session.setAttribute("permissions", rs.getString("permissions"));
                //System.out.println("LOOK AT THIS: " + rs.getString("permissions"));
                
                session.setAttribute("username", rs.getString("name"));
                //System.out.println("LOOK AT THIS: " + rs.getString("name"));
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}
