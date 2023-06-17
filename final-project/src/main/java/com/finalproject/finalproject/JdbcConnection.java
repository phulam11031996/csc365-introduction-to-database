package com.finalproject.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConnection {

    private Connection connect;
    private PreparedStatement preparedStatement;
    private final String CONNECTION = "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/phutai?user=phutai&password=phutai";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    public boolean makeConnection() {
        try {
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(CONNECTION);
            return true;
        } catch (Exception e) {
            printSQLException((SQLException) e);
            return false;
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (ex instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public Connection getConnection() {
        return this.connect;
    }

    public PreparedStatement getPreparedStatement() {
        return this.preparedStatement;
    }

}
