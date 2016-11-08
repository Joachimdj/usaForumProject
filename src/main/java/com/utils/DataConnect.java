package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

    public static Connection getConnection() {
        try {
            Class.forName(DB.DRIVER);
            Connection con = DriverManager.getConnection(
                    DB.URL, DB.USER, DB.PW);
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
