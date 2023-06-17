package com.finalproject.finalproject;

public class Table {

    private String sid = null;
    private String pid = null;
    private String cid = null;
    private String firstName = null;
    private String lastName = null;
    private String dob = null;
    private String email = null;
    private String major = null;
    private String courseLabel = null;
    private String courseName = null;
    private String quarter = null;
    private String schoolYear = null;
    private String grade = null;
    private String gpa = null;

    // Search Student Table
    public void initSearchStudentTable(String sid, String firstName, String lastName, String email, String dob,
            String major) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    // Search Student Course Table
    public void initSearchStudentCourseTable(String pid, String courseLabel, String courseName, String quarter,
            String schoolYear, String grade) {
        this.pid = pid;
        this.courseLabel = courseLabel;
        this.courseName = courseName;
        this.quarter = quarter;
        this.schoolYear = schoolYear;
        this.grade = grade;
    }

    // Search Professor Table
    public void initSearchProfessorTable(String pid, String firstName, String lastName, String email, String dob,
            String major) {
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    // Search Professor Course Table
    public void initSearchProfessorCourseTable(String cid, String courseLabel, String courseName, String quarter,
            String schoolYear) {
        this.cid = cid;
        this.courseLabel = courseLabel;
        this.courseName = courseName;
        this.quarter = quarter;
        this.schoolYear = schoolYear;
    }

    // Search All Students in a Class
    public void initSearchStudentInClass(String sid, String firstName, String lastName, String grade) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    // Search All Courses By Professor Id
    public void initSearchCoursesByProfessorId(String cid, String courseLabel, String courseName, String pid,
            String quarter, String schoolYear) {
        this.cid = cid;
        this.courseLabel = courseLabel;
        this.courseName = courseName;
        this.pid = pid;
        this.quarter = quarter;
        this.schoolYear = schoolYear;
    }

    // Find Students By GPA
    public void initFindStudentByGpa(String sid, String firstName, String lastName, String major, String gpa) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.gpa = gpa;
    }

    // Find Professors By Class GPA
    public void initFindProfessorByClassGpa(String pid, String firstName, String lastName, String major, String gpa) {
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.gpa = gpa;
    }

    // Remove Course
    public void initTableRemoveCourse(String cid, String courseLabel, String courseName, String quarter,
            String schoolYear, String pid) {
        this.cid = cid;
        this.courseLabel = courseLabel;
        this.courseName = courseName;
        this.quarter = quarter;
        this.schoolYear = schoolYear;
        this.pid = pid;
    }

    // Remove Professor
    public void initTableRemoveProfessor(String pid, String firstName, String lastName, String email, String dob,
            String major) {
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    // Remove Student
    public void initTableRemoveStudent(String sid, String firstName, String lastName, String email, String dob,
            String major) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    // getters
    public String getSid() {
        return sid;
    }

    public String getPid() {
        return pid;
    }

    public String getCid() {
        return cid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public String getCourseLabel() {
        return courseLabel;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getQuarter() {
        return quarter;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public String getGrade() {
        return grade;
    }

    public String getGpa() {
        return gpa;
    }

    // setters
    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setCourseLabel(String courseLabel) {
        this.courseLabel = courseLabel;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

}