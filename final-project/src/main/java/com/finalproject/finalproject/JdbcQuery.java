package com.finalproject.finalproject;

import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JdbcQuery {

    JdbcConnection jdbcConnection = new JdbcConnection();

    public JdbcQuery() {
    }

    public void createNewTable(
            TableView<Table> table,
            TableColumn<Table, String> column1,
            TableColumn<Table, String> column2,
            TableColumn<Table, String> column3,
            TableColumn<Table, String> column4,
            TableColumn<Table, String> column5,
            TableColumn<Table, String> column6,
            String columnName1,
            String columnName2,
            String columnName3,
            String columnName4,
            String columnName5,
            String columnName6) {
        if (column1 != null) {
            column1.setCellValueFactory(new PropertyValueFactory<>(columnName1));
            table.getColumns().add(column1);
        }
        if (column2 != null) {
            column2.setCellValueFactory(new PropertyValueFactory<>(columnName2));
            table.getColumns().add(column2);
        }
        if (column3 != null) {
            column3.setCellValueFactory(new PropertyValueFactory<>(columnName3));
            table.getColumns().add(column3);
        }
        if (column4 != null) {
            column4.setCellValueFactory(new PropertyValueFactory<>(columnName4));
            table.getColumns().add(column4);
        }
        if (column5 != null) {
            column5.setCellValueFactory(new PropertyValueFactory<>(columnName5));
            table.getColumns().add(column5);
        }
        if (column6 != null) {
            column6.setCellValueFactory(new PropertyValueFactory<>(columnName6));
            table.getColumns().add(column6);
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    // Search Students Table
    public String queryDataToSearchStudentTable(
            String sid,
            TableView<Table> table1,
            TableView<Table> table2) {
        try {
            table1.getItems().clear();
            table2.getItems().clear();

            String sql = "SELECT * FROM student WHERE sid = ?;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, sid);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rssid = rs.getString("sid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsemail = rs.getString("email");
                String rsdob = rs.getString("dob");
                String rsmajor = rs.getString("major");

                Table newTable1 = new Table();
                newTable1.initSearchStudentTable(rssid, rsfirstName, rslastName, rsemail, rsdob, rsmajor);
                table1.getItems().add(newTable1);
            }

            sql = "SELECT DISTINCT " +
                    "course.pid, course.courseLabel, course.courseName, course.quarter, course.schoolYear, grade.grade "
                    +
                    "FROM grade INNER JOIN course on grade.cid = course.cid " +
                    "WHERE grade.sid = ?;";

            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, sid);
            rs = preStatement.executeQuery();

            while (rs.next()) {
                String rspid = rs.getString("pid");
                String rscourseLabel = rs.getString("courseLabel");
                String rscourseName = rs.getString("courseName");
                String rsquarter = rs.getString("quarter");
                String rsschoolYear = rs.getString("schoolYear");
                String rsgrade = rs.getString("grade");
                if (rsgrade.equals("0.00"))
                    rsgrade = "F";
                if (rsgrade.equals("1.00"))
                    rsgrade = "D";
                if (rsgrade.equals("2.00"))
                    rsgrade = "C";
                if (rsgrade.equals("3.00"))
                    rsgrade = "B";
                if (rsgrade.equals("4.00"))
                    rsgrade = "A";

                Table newTable2 = new Table();
                newTable2.initSearchStudentCourseTable(rspid, rscourseLabel, rscourseName, rsquarter, rsschoolYear,
                        rsgrade);
                table2.getItems().add(newTable2);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }

    }

    // Search Professors Table
    public String queryDataToSearchProfessorTable(
            String pid,
            TableView<Table> table1,
            TableView<Table> table2) {
        try {
            table1.getItems().clear();
            table2.getItems().clear();

            String sql = "SELECT * FROM professor WHERE pid = ?;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, pid);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rspid = rs.getString("pid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsemail = rs.getString("email");
                String rsdob = rs.getString("dob");
                String rsmajor = rs.getString("major");

                Table newTable1 = new Table();
                newTable1.initSearchProfessorTable(rspid, rsfirstName, rslastName, rsemail, rsdob, rsmajor);
                table1.getItems().add(newTable1);
            }

            sql = "select distinct course.cid, course.courseLabel, course.courseName, course.quarter, course.schoolYear "
                    +
                    "from course " +
                    "where course.pid = ?;";

            preStatement = connect.prepareStatement(sql);
            preStatement.setInt(1, Integer.valueOf(pid));
            rs = preStatement.executeQuery();

            while (rs.next()) {
                String rscid = rs.getString("cid");
                String rscourseLabel = rs.getString("courseLabel");
                String rscourseName = rs.getString("courseName");
                String rsquarter = rs.getString("quarter");
                String rsschoolYear = rs.getString("schoolYear");
                Table newTable2 = new Table();

                newTable2.initSearchProfessorCourseTable(rscid, rscourseLabel, rscourseName, rsquarter, rsschoolYear);
                table2.getItems().add(newTable2);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }

    }

    // Search Students in Class
    public String queryDataToSearchStudentsInClass(
            String cid,
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String query = "SELECT distinct A.sid, A.firstName, A.lastName, B.grade " +
                    "FROM student A, grade B " +
                    "WHERE B.cid=? and B.sid = A.sid;";

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, cid);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rssid = rs.getString("sid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsgrade = rs.getString("grade");

                Table newTable = new Table();
                newTable.initSearchStudentInClass(rssid, rsfirstName, rslastName, rsgrade);
                table.getItems().add(newTable);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }
    }

    // Search Course By Quarter And School Year
    public String queryDataToSearchCourseByQuarterAndSchoolYear(
            String schoolYear,
            String quarter,
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String query = "SELECT * FROM course WHERE schoolYear = ? AND quarter = ?;";

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, schoolYear);
            preStatement.setString(2, quarter);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rscid = rs.getString("cid");
                String rscourseLabel = rs.getString("courseLabel");
                String rscourseName = rs.getString("courseName");
                String rspid = rs.getString("pid");
                String rsquarter = rs.getString("quarter");
                String rsschoolYear = rs.getString("schoolYear");

                Table newTable = new Table();
                newTable.initSearchCoursesByProfessorId(rscid, rscourseLabel, rscourseName, rspid, rsquarter,
                        rsschoolYear);
                table.getItems().add(newTable);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }
    }

    // Find Students by GPA and Major
    public String queryDataToFindStudentsByGpaAndMajor(
            double gpa,
            String major,
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String sql1 = "SELECT student.sid, student.firstName, student.lastName, student.major, AVG(grade.grade) GPA "
                    +
                    "FROM grade " +
                    "INNER JOIN student on grade.sid = student.sid " +
                    "WHERE student.major = ? " +
                    "GROUP BY grade.sid " +
                    "HAVING AVG(grade.grade) > ? " +
                    "ORDER BY AVG(grade.grade) DESC;";
            String sql2 = "SELECT student.sid, student.firstName, student.lastName, student.major, AVG(grade.grade) GPA "
                    +
                    "FROM grade " +
                    "INNER JOIN student on grade.sid = student.sid " +
                    "GROUP BY grade.sid " +
                    "HAVING AVG(grade.grade) > ? " +
                    "ORDER BY AVG(grade.grade) DESC;";

            String tempgpa = String.valueOf(gpa);
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();

            if (major.equals("All")) {
                preStatement = connect.prepareStatement(sql2);
                preStatement.setString(1, tempgpa);
            } else {
                preStatement = connect.prepareStatement(sql1);
                preStatement.setString(1, major);
                preStatement.setString(2, tempgpa);
            }
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rssid = rs.getString("sid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsmajor = rs.getString("major");
                String rsgpa = rs.getString("gpa");

                Table newTable = new Table();
                newTable.initFindStudentByGpa(rssid, rsfirstName, rslastName, rsmajor, rsgpa);
                table.getItems().add(newTable);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }

    }

    // Find Professors by Class GPA and Department
    public String queryToFindProfessorsByClassGpaAndDepartment(
            double gpa,
            String major,
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String sql1 = "select professor.pid, professor.firstName, professor.lastName, professor.major, professor.major, avg(grade.grade) as gpa "
                    +
                    "from professor natural join course natural join grade " +
                    "where professor.major = ?" +
                    "group by professor.pid, professor.firstName, professor.lastName, professor.major, professor.major "
                    +
                    "having gpa >= ?;";

            String sql2 = "select professor.pid, professor.firstName, professor.lastName, professor.major, professor.major, avg(grade.grade) as gpa "
                    +
                    "from professor natural join course natural join grade " +
                    "group by professor.pid, professor.firstName, professor.lastName, professor.major, professor.major "
                    +
                    "having gpa >= ?;";

            String tempgpa = String.valueOf(gpa);
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            if (major.equals("All")) {
                preStatement = connect.prepareStatement(sql2);
                preStatement.setString(1, tempgpa);
            } else {
                preStatement = connect.prepareStatement(sql1);
                preStatement.setString(1, major);
                preStatement.setString(2, tempgpa);
            }
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rspid = rs.getString("pid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsmajor = rs.getString("major");
                String rsgpa = rs.getString("gpa");

                Table newTable = new Table();
                newTable.initFindProfessorByClassGpa(rspid, rsfirstName, rslastName, rsmajor, rsgpa);
                table.getItems().add(newTable);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }

    }

    // Remove Course Query
    public String queryToTableRemoveCourse(
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String sql = "SELECT * FROM course";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rscid = rs.getString("cid");
                String rscourseLabel = rs.getString("courseLabel");
                String rscourseName = rs.getString("courseName");
                String rsquarter = rs.getString("quarter");
                String rsschoolYear = rs.getString("schoolYear");
                String rspid = rs.getString("pid");

                Table newTable = new Table();
                newTable.initTableRemoveCourse(rscid, rscourseLabel, rscourseName, rsquarter, rsschoolYear, rspid);
                table.getItems().add(newTable);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }

    }

    // Remove Professor Query
    public String queryToTableRemoveProfessor(
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String sql = "SELECT * FROM professor";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rspid = rs.getString("pid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsemail = rs.getString("email");
                String rsdob = rs.getString("dob");
                String rsmajor = rs.getString("major");

                Table newTable = new Table();
                newTable.initTableRemoveProfessor(rspid, rsfirstName, rslastName, rsemail, rsdob, rsmajor);
                table.getItems().add(newTable);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }

    }

    // Remove Professor Query
    public String queryToTableRemoveStudent(
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String sql = "SELECT * FROM student";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rssid = rs.getString("sid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsemail = rs.getString("email");
                String rsdob = rs.getString("dob");
                String rsmajor = rs.getString("major");

                Table newTable = new Table();
                newTable.initTableRemoveStudent(rssid, rsfirstName, rslastName, rsemail, rsdob, rsmajor);
                table.getItems().add(newTable);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return String.valueOf(e.getErrorCode());
        }

    }

}
