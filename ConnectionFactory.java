package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ConnectionFactory {

    

public static final String URL = "jdbc:mysql://localhost:3306/sakila";

public static final String USER = "dragomundoh";

public static final String PASS = "Kstujdfw34hgacvmaste";



public static Connection getConnection()

 {

   try {

       DriverManager.registerDriver(new Driver());

       return DriverManager.getConnection(URL, USER, PASS);

   } catch (SQLException ex) {

       throw new RuntimeException("Error connecting to the database", ex);

   }

 }



}