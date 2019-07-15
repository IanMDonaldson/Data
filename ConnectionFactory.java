package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.mariadb.jdbc.*;
@SuppressWarnings("unused")
public class ConnectionFactory {

    public static Connection getConnection()
    {
      try {
          Context initContext = new InitialContext();
          Context envContext = (Context) initContext.lookup("java:comp/env");
          DataSource ds = (DataSource)envContext.lookup("jdbc/sakila");
          
          return ds.getConnection();
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      } catch (NamingException e) {
		e.printStackTrace();
	}
      return null;
    }
}