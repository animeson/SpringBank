package com.webApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDb {

    private static final String URL = "jdbc:postgresql://localhost:5432/db_bank";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connection;

    static {
        try {
            try {
                Class.forName("org.postgresql.Driver");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
