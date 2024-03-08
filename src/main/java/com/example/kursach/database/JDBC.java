package com.example.kursach.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC
{
    public static Connection connection = null;

    public static void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("MyS0L JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("There is your MySQL JDBC Driver?");
            e.printStackTrace();
            throw new SQLException();
        }
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost/курсовая", "root", "1234");
        if (connection == null) {
            throw new SQLException();
        } else {
            //System.out.println("Successfully connected");
        }
    }

    public static void close() {
        try
        {
            if(connection != null)
            {
                connection.close();
                //System.out.println("Отключаемся от БД");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to close JDBC connection!");
        }
    }
}


