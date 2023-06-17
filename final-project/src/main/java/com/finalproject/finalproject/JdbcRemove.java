package com.finalproject.finalproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.finalproject.finalproject.JdbcConnection.printSQLException;

public class JdbcRemove {

    JdbcConnection connection = new JdbcConnection();

    public String removeByStudentID(String studentID) {
        try {
            this.connection.makeConnection();
            String query = "DELETE FROM student WHERE sid=?;";
            Connection connect = this.connection.getConnection();
            PreparedStatement preStatement = this.connection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, studentID);
            int rs = preStatement.executeUpdate();
            connect.close();
            return String.valueOf(rs);
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

    public String removeByProfessorID(String professorID) {
        try {
            this.connection.makeConnection();
            String query = "DELETE FROM professor where pid=?;";
            Connection connect = this.connection.getConnection();
            PreparedStatement preStatement = this.connection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, professorID);
            int rs = preStatement.executeUpdate();
            connect.close();
            return String.valueOf(rs);
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

    public String removeByCourseID(String courseID) {
        try {
            String query = "DELETE FROM course where cid=?;";
            this.connection.makeConnection();
            Connection connect = this.connection.getConnection();
            PreparedStatement preStatement = this.connection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, courseID);
            int rs = preStatement.executeUpdate();
            connect.close();
            return String.valueOf(rs);
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

}
