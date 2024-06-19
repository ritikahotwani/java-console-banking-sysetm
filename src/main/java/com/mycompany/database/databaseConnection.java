package com.mycompany.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnection {
    public static Connection provideConnection() {
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/bankingsystem";
        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (Exception e) {
            System.out.println("Error in connection" + e.getMessage()) ;
        }
        return connection;
    }
}
