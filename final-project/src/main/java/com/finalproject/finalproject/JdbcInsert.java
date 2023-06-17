package com.finalproject.finalproject;

import java.sql.*;

public class JdbcInsert {

    private JdbcConnection jdbcConnection = new JdbcConnection();

    public JdbcInsert() {
    }

    public String insertStudent(String firstName, String lastName, String email, String dob, String major) {
        try {
            String sql = "INSERT INTO " +
                    "student (firstName, lastName, email, dob, major) " +
                    "VALUES " +
                    "( ?, ?, ?, ?, ? );";

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, firstName);
            preStatement.setString(2, lastName);
            preStatement.setString(3, email);
            preStatement.setString(4, dob);
            preStatement.setString(5, major);
            preStatement.executeUpdate();
            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }
    }

    public String insertProfessor(String firstName, String lastName, String email, String dob, String major) {
        try {
            String sql = "INSERT INTO " +
                    "professor (firstName, lastName, email, dob, major) " +
                    "VALUES " +
                    "( ?, ?, ?, ?, ?);";

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, firstName);
            preStatement.setString(2, lastName);
            preStatement.setString(3, email);
            preStatement.setString(4, dob);
            preStatement.setString(5, major);
            preStatement.executeUpdate();
            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }
    }

    public String insertCourse(String courseTitle, String courseName, String professorId, String quarter,
            String schoolYear) {
        try {
            String sql = "INSERT INTO " +
                    "course (courseLabel, courseName, pid, quarter, schoolYear) " +
                    "VALUES " +
                    "( ?, ?, ?, ?, ?);";

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, courseTitle);
            preStatement.setString(2, courseName);
            preStatement.setInt(3, Integer.valueOf(professorId));
            preStatement.setString(4, quarter);
            preStatement.setInt(5, Integer.valueOf(schoolYear));
            preStatement.executeUpdate();
            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }
    }

    public String insertGrade(String courseId, String studentId, String grade) {
        try {
            String sql = "INSERT INTO " +
                    "grade (cid, sid, grade) " +
                    "VALUES " +
                    "( ?, ?, ?);";

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setInt(1, Integer.valueOf(courseId));
            preStatement.setInt(2, Integer.valueOf(studentId));

            if (grade.equals("A"))
                preStatement.setDouble(3, 4.00);
            if (grade.equals("B"))
                preStatement.setDouble(3, 3.00);
            if (grade.equals("C"))
                preStatement.setDouble(3, 2.00);
            if (grade.equals("D"))
                preStatement.setDouble(3, 1.00);
            if (grade.equals("F"))
                preStatement.setDouble(3, 0.00);

            preStatement.executeUpdate();
            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }
    }

}
