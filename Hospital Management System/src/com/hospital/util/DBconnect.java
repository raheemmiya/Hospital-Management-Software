package com.hospital.util;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class DBconnect {

    public static Connection connect(){
        Connection connection = null;

        try {

            String url="jdbc:mysql://localhost:3306/hospitalmanagementsystem";
            String username= "root";
            String password= "root";

            connection = DriverManager.getConnection(url, username, password);       
                 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return connection;
    }
    public static void main(String[] args) {
        connect();
    }

}
