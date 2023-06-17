package com.finalproject.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DashboardController implements Initializable {

    private final JdbcInsert jbdcInsert = new JdbcInsert();
    private final JdbcRemove jbdcRemove = new JdbcRemove();
    private final JdbcQuery jdbcQuery = new JdbcQuery();

    @FXML
    private TabPane tabPane = new TabPane();

    void setTabPaneLeftTabsHorizontal(TabPane tabPane) {

        tabPane.setSide(Side.LEFT);
        tabPane.setRotateGraphic(true);

        Label l = null;
        StackPane stp = null;
        for (Tab t : tabPane.getTabs()) {
            l = new Label(t.getText());
            l.setRotate(90);
            stp = new StackPane(new Group(l));
            stp.setRotate(90);
            t.setGraphic(stp);
            t.setText("");
        }

        tabPane.setTabMinHeight(160);
        tabPane.setTabMaxHeight(160);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setTabPaneLeftTabsHorizontal(this.tabPane);

        this.majorAS.getItems().addAll(
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer",
                "Information Technology",
                "Information Systems Security",
                "Computer Engineering");

        this.majorAP.getItems().addAll(
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer",
                "Information Technology",
                "Information Systems Security",
                "Computer Engineering");

        this.quarterAC.getItems().addAll("Fall", "Winter", "Spring", "Summer");
        this.quarterSCL.getItems().addAll("Fall", "Winter", "Spring", "Summer");

        for (int i = 2025; i > 2000; i--) {
            this.schoolyearAC.getItems().add(String.valueOf(i));
            this.schoolYearSCL.getItems().add(String.valueOf(i));
        }

        this.gradeAG.getItems().addAll("A", "B", "C", "D", "E", "F");

        this.findStudentGpaChoiceBox.setValue("All");
        this.findStudentGpaChoiceBox.getItems().addAll(
                "All",
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer",
                "Information Technology",
                "Information Systems Security",
                "Computer Engineering");

        this.findProfessorGpaChoiceBox.setValue("All");
        this.findProfessorGpaChoiceBox.getItems().addAll(
                "All",
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer",
                "Information Technology",
                "Information Systems Security",
                "Computer Engineering");

        this.jdbcQuery.createNewTable(this.searchStudentTable,
                this.sstColumn1,
                this.sstColumn2,
                this.sstColumn3,
                this.sstColumn4,
                this.sstColumn5,
                this.sstColumn6, "sid", "firstName", "lastName", "email", "dob", "major");

        this.jdbcQuery.createNewTable(
                this.searchStudentCourseTable,
                this.sstcColumn1,
                this.sstcColumn2,
                this.sstcColumn3,
                this.sstcColumn4,
                this.sstcColumn5,
                this.sstcColumn6, "pid", "courseLabel", "courseName", "quarter", "schoolYear", "grade");

        this.jdbcQuery.createNewTable(this.searchProfessorTable,
                this.sptColumn1,
                this.sptColumn2,
                this.sptColumn3,
                this.sptColumn4,
                this.sptColumn5,
                this.sptColumn6, "pid", "firstName", "lastName", "email", "dob", "major");

        this.jdbcQuery.createNewTable(
                this.searchProfessorCourseTable,
                this.spctColumn1,
                this.spctColumn2,
                this.spctColumn3,
                this.spctColumn4,
                this.spctColumn5,
                null, "cid", "courseLabel", "courseName", "quarter", "schoolYear", null);

        this.jdbcQuery.createNewTable(
                tableCourse,
                colStudentIDSearchCourse,
                colFNSearchCourse,
                colLNSearchCourse,
                colGradeSearchCourse,
                null,
                null, "sid", "firstName", "lastName", "grade", null, null);

        this.jdbcQuery.createNewTable(
                tableCourseFindCourse,
                colCourseIDFindCourse,
                colCourseLabelFindCourse,
                colCourseNameFindCourse,
                colInstructorIDFindCourse,
                colQuarterFindCourse,
                colYearFindCourse, "cid", "courseLabel", "courseName", "pid", "quarter", "schoolYear");

        this.jdbcQuery.createNewTable(
                this.findStudentGpaTable,
                this.fsgtColumn1,
                this.fsgtColumn2,
                this.fsgtColumn3,
                this.fsgtColumn4,
                this.fsgtColumn5,
                null, "sid", "firstName", "lastName", "major", "gpa", null);

        this.jdbcQuery.createNewTable(
                this.findProfessorGpaTable,
                this.fpbgColumn1,
                this.fpbgColumn2,
                this.fpbgColumn3,
                this.fpbgColumn4,
                this.fpbgColumn5,
                null, "pid", "firstName", "lastName", "major", "gpa", null);

        this.jdbcQuery.createNewTable(
                this.tableRemoveCourse,
                this.rcColumn1,
                this.rcColumn2,
                this.rcColumn3,
                this.rcColumn4,
                this.rcColumn5,
                this.rcColumn6, "cid", "courseLabel", "courseName", "pid", "quarter", "schoolYear");

        this.jdbcQuery.createNewTable(
                this.tableRemoveProfessor,
                this.rpColumn1,
                this.rpColumn2,
                this.rpColumn3,
                this.rpColumn4,
                this.rpColumn5,
                this.rpColumn6, "pid", "firstName", "lastName", "email", "dob", "major");

        this.jdbcQuery.createNewTable(
                this.tableRemoveStudent,
                this.rsColumn1,
                this.rsColumn2,
                this.rsColumn3,
                this.rsColumn4,
                this.rsColumn5,
                this.rsColumn6, "sid", "firstName", "lastName", "email", "dob", "major");

        this.findStudentGpaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                sliderLabel.setText(String.format("%.2f", newValue));
            }
        });

        this.findProfessorGpaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                sliderLabel2.setText(String.format("%.2f", newValue));
            }
        });

        this.jdbcQuery.queryToTableRemoveCourse(this.tableRemoveCourse);
        this.jdbcQuery.queryToTableRemoveProfessor(this.tableRemoveProfessor);
        this.jdbcQuery.queryToTableRemoveStudent(this.tableRemoveStudent);

    }

    // Add Student 
    @FXML
    private Label errorAddStudent;
    @FXML
    private TextField firstNameAS;
    @FXML
    private TextField lastNameAS;
    @FXML
    private TextField emailAS;
    @FXML
    private DatePicker dobAS;
    @FXML
    private ChoiceBox<String> majorAS;

    @FXML
    private void onSubmitAddStudent(ActionEvent actionEvent) {
        this.errorAddStudent.setTextFill(Color.color(1, 0, 0));
        if (nameValidator(this.firstNameAS.getText(), this.errorAddStudent,
                "First name must be 3 or more characters long.")
                &&
                nameValidator(this.lastNameAS.getText(), this.errorAddStudent,
                        "Last name must be 3 or more characters long.")
                &&
                emailValidator(this.emailAS.getText(), this.errorAddStudent, "Email is invalid.") &&
                datePickerValidator(this.dobAS.getValue(), this.errorAddStudent, "Day of birth field is required.") &&
                choiceBoxPicker(this.majorAS.getValue(), this.errorAddStudent, "Major field is required.")) {

            String message = this.jbdcInsert.insertStudent(this.firstNameAS.getText(),
                    this.lastNameAS.getText(),
                    this.emailAS.getText(),
                    this.dobAS.getValue().toString(),
                    this.majorAS.getValue());
            if (message == null) {
                this.errorAddStudent.setTextFill(Color.color(0, 0, 1));
                this.errorAddStudent.setText("A new student is added.");
            } else {
                this.errorAddStudent.setTextFill(Color.color(1, 0, 0));
                this.errorAddStudent.setText("Error Code: " + message);
            }
            this.clear();
        }
        this.jdbcQuery.queryToTableRemoveStudent(this.tableRemoveStudent);
    }

    // Add Professor
    @FXML
    private Label errorAddProfessor;
    @FXML
    private TextField firstNameAP;
    @FXML
    private TextField lastNameAP;
    @FXML
    private TextField emailAP;
    @FXML
    private DatePicker dobAP;
    @FXML
    private ChoiceBox<String> majorAP;

    @FXML
    private void onSubmitAddProfessor(ActionEvent actionEvent) {
        this.errorAddProfessor.setTextFill(Color.color(1, 0, 0));
        if (nameValidator(this.firstNameAP.getText(), this.errorAddProfessor,
                "First name must be 3 or more characters long.")
                &&
                nameValidator(this.lastNameAP.getText(), this.errorAddProfessor,
                        "Last name must be 3 or more characters long.")
                &&
                emailValidator(this.emailAP.getText(), this.errorAddProfessor, "Email is invalid.") &&
                datePickerValidator(this.dobAP.getValue(), this.errorAddProfessor, "Day of birth field is required.") &&
                choiceBoxPicker(this.majorAP.getValue(), this.errorAddProfessor, "Department field is required.")) {

            String message = this.jbdcInsert.insertProfessor(this.firstNameAP.getText(),
                    this.lastNameAP.getText(),
                    this.emailAP.getText(),
                    this.dobAP.getValue().toString(),
                    this.majorAP.getValue());
            if (message == null) {
                this.errorAddProfessor.setTextFill(Color.color(0, 0, 1));
                this.errorAddProfessor.setText("A new professor is added.");
            } else {
                this.errorAddProfessor.setTextFill(Color.color(1, 0, 0));
                this.errorAddProfessor.setText("Error Code: " + message);
            }
            this.clear();
        }
        this.jdbcQuery.queryToTableRemoveProfessor(this.tableRemoveProfessor);
    }

    // Add Course
    @FXML
    private Label errorAddCourse;
    @FXML
    private TextField courseLabelAC;
    @FXML
    private TextField courseNameAC;
    @FXML
    private TextField instructorIdAC;
    @FXML
    private ChoiceBox<String> quarterAC;
    @FXML
    private ChoiceBox<String> schoolyearAC;

    @FXML
    private void onSubmitAddCourse(ActionEvent actionEvent) {
        this.errorAddCourse.setTextFill(Color.color(1, 0, 0));
        if (nameValidator(this.courseLabelAC.getText(), this.errorAddCourse,
                "Course title must be 3 or more characters long.") &&
                nameValidator(this.courseNameAC.getText(), this.errorAddCourse,
                        "Course name must be 3 or more characters long.")
                &&
                idValidator(this.instructorIdAC.getText(), this.errorAddCourse,
                        "Instructor ID must contain numbers only.")
                &&
                choiceBoxPicker(this.quarterAC.getValue(), this.errorAddCourse, "Quarter field is required.") &&
                choiceBoxPicker(this.schoolyearAC.getValue(), this.errorAddCourse, "School year field is required.")) {

            String message = this.jbdcInsert.insertCourse(this.courseLabelAC.getText(),
                    this.courseNameAC.getText(),
                    this.instructorIdAC.getText(),
                    this.quarterAC.getValue().toString(),
                    this.schoolyearAC.getValue().toString());
            if (message == null) {
                this.errorAddCourse.setTextFill(Color.color(0, 0, 1));
                this.errorAddCourse.setText("A new course is added.");
            } else {
                this.errorAddCourse.setTextFill(Color.color(1, 0, 0));
                this.errorAddCourse.setText("Error Code: " + message);
            }
            this.clear();
        }
        this.jdbcQuery.queryToTableRemoveCourse(this.tableRemoveCourse);
    }

    // Add Grade
    @FXML
    private Label errorAddGrade;
    @FXML
    private TextField courseIDAG;
    @FXML
    private TextField studentIDAG;
    @FXML
    private ChoiceBox<String> gradeAG;

    @FXML
    private void onSubmitAddGrade(ActionEvent actionEvent) {
        this.errorAddGrade.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.courseIDAG.getText(), this.errorAddGrade, "Course ID must contain numbers only.") &&
                idValidator(this.studentIDAG.getText(), this.errorAddGrade, "Student ID must contain numbers only.") &&
                choiceBoxPicker(this.gradeAG.getValue(), this.errorAddGrade, "School year field is required.")) {

            String message = this.jbdcInsert.insertGrade(this.courseIDAG.getText(), this.studentIDAG.getText(),
                    this.gradeAG.getValue());
            if (message == null) {
                this.errorAddGrade.setTextFill(Color.color(0, 0, 1));
                this.errorAddGrade.setText("A new grade is added.");
            } else {
                this.errorAddGrade.setTextFill(Color.color(1, 0, 0));
                this.errorAddGrade.setText("Error Code: " + message);
            }
            this.clear();
        }

    }

    // Search Student
    @FXML
    private Label errorSearchStudent;
    @FXML
    private TextField studentIdSS;
    @FXML
    private TableView<Table> searchStudentTable;
    @FXML
    private final TableColumn<Table, String> sstColumn1 = new TableColumn<>("Student ID");
    @FXML
    private final TableColumn<Table, String> sstColumn2 = new TableColumn<>("First Name");
    @FXML
    private final TableColumn<Table, String> sstColumn3 = new TableColumn<>("Last Name");
    @FXML
    private final TableColumn<Table, String> sstColumn4 = new TableColumn<>("Email");
    @FXML
    private final TableColumn<Table, String> sstColumn5 = new TableColumn<>("Day of Birth");
    @FXML
    private final TableColumn<Table, String> sstColumn6 = new TableColumn<>("Major");
    @FXML
    private TableView<Table> searchStudentCourseTable;
    @FXML
    private final TableColumn<Table, String> sstcColumn1 = new TableColumn<>("Professor ID");
    @FXML
    private final TableColumn<Table, String> sstcColumn2 = new TableColumn<>("Course Label");
    @FXML
    private final TableColumn<Table, String> sstcColumn3 = new TableColumn<>("Course Name");
    @FXML
    private final TableColumn<Table, String> sstcColumn4 = new TableColumn<>("Quarter");
    @FXML
    private final TableColumn<Table, String> sstcColumn5 = new TableColumn<>("School Year");
    @FXML
    private final TableColumn<Table, String> sstcColumn6 = new TableColumn<>("Grade");

    @FXML
    private void onSubmitSearchStudent(ActionEvent actionEvent) {
        this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.studentIdSS.getText(), this.errorSearchStudent, "Student ID must contain numbers only.")) {
            String message = this.jdbcQuery.queryDataToSearchStudentTable(this.studentIdSS.getText(),
                    this.searchStudentTable,
                    this.searchStudentCourseTable);

            if (message == null) {
                this.errorSearchStudent.setTextFill(Color.color(0, 0, 1));
                this.errorSearchStudent.setText("Searching student with ID: " + this.studentIdSS.getText());
            } else {
                this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
                this.errorSearchStudent.setText("Error Code: " + message);
            }
            this.clear();
        }

    }

    // Search Professor
    @FXML
    private Label errorSearchProfessor;
    @FXML
    private TextField professorIdSP;
    @FXML
    private TableView<Table> searchProfessorTable;
    @FXML
    private final TableColumn<Table, String> sptColumn1 = new TableColumn<>("Professor ID");
    @FXML
    private final TableColumn<Table, String> sptColumn2 = new TableColumn<>("First Name");
    @FXML
    private final TableColumn<Table, String> sptColumn3 = new TableColumn<>("Last Name");
    @FXML
    private final TableColumn<Table, String> sptColumn4 = new TableColumn<>("Email");
    @FXML
    private final TableColumn<Table, String> sptColumn5 = new TableColumn<>("Day of Birth");
    @FXML
    private final TableColumn<Table, String> sptColumn6 = new TableColumn<>("Department");
    @FXML
    private TableView<Table> searchProfessorCourseTable;
    @FXML
    private final TableColumn<Table, String> spctColumn1 = new TableColumn<>("Course ID");
    @FXML
    private final TableColumn<Table, String> spctColumn2 = new TableColumn<>("Course Label");
    @FXML
    private final TableColumn<Table, String> spctColumn3 = new TableColumn<>("Course Name");
    @FXML
    private final TableColumn<Table, String> spctColumn4 = new TableColumn<>("Quarter");
    @FXML
    private final TableColumn<Table, String> spctColumn5 = new TableColumn<>("School Year");

    @FXML
    private void onSubmitSearchProfessor(ActionEvent actionEvent) {
        this.errorSearchProfessor.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.professorIdSP.getText(), this.errorSearchProfessor,
                "Professor ID must contain numbers only.")) {

            String message = this.jdbcQuery.queryDataToSearchProfessorTable(this.professorIdSP.getText(),
                    this.searchProfessorTable,
                    this.searchProfessorCourseTable);
            if (message == null) {
                this.errorSearchProfessor.setTextFill(Color.color(0, 0, 1));
                this.errorSearchProfessor.setText("Searching professor with ID: " + this.professorIdSP.getText());
            } else {
                this.errorSearchProfessor.setTextFill(Color.color(1, 0, 0));
                this.errorSearchProfessor.setText("Error Code: " + message);
            }
            this.clear();
        }

    }

    // Search All Students in a Class
    @FXML
    private TextField courseIDSSC;
    @FXML
    private Label errorSearchStudentsInClass;
    @FXML
    private TableView<Table> tableCourse;
    @FXML
    private final TableColumn<Table, String> colStudentIDSearchCourse = new TableColumn<>("Student Id");
    @FXML
    private final TableColumn<Table, String> colFNSearchCourse = new TableColumn<>("First Name");
    @FXML
    private final TableColumn<Table, String> colLNSearchCourse = new TableColumn<>("Last Name");
    @FXML
    private final TableColumn<Table, String> colGradeSearchCourse = new TableColumn<>("Grade");

    @FXML
    private void onSubmitSearchStudentsInClass(ActionEvent actionEvent) {
        this.errorSearchStudentsInClass.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.courseIDSSC.getText(), this.errorSearchStudentsInClass,
                "Course ID must contain numbers only.")) {
            String message = this.jdbcQuery.queryDataToSearchStudentsInClass(this.courseIDSSC.getText(),
                    tableCourse);
            if (message == null) {
                this.errorSearchStudent.setTextFill(Color.color(0, 0, 1));
                this.errorSearchStudent.setText("Searching student with Course ID: " + this.courseIDSSC.getText());
            } else {
                this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
                this.errorSearchStudent.setText("Error Code: " + message);
            }
            this.clear();
        }

    }

    // Search All Course By Quarter and School Year
    @FXML
    private Label errorSearchCourseList;
    @FXML
    private ChoiceBox<String> schoolYearSCL;
    @FXML
    private ChoiceBox<String> quarterSCL;
    @FXML
    private TableView<Table> tableCourseFindCourse;
    @FXML
    private final TableColumn<Table, String> colCourseIDFindCourse = new TableColumn<>("Course ID");
    @FXML
    private final TableColumn<Table, String> colCourseLabelFindCourse = new TableColumn<>("Course Label");
    @FXML
    private final TableColumn<Table, String> colCourseNameFindCourse = new TableColumn<>("Course Name");
    @FXML
    private final TableColumn<Table, String> colInstructorIDFindCourse = new TableColumn<>("Instructor ID");
    @FXML
    private final TableColumn<Table, String> colQuarterFindCourse = new TableColumn<>("Quarter");
    @FXML
    private final TableColumn<Table, String> colYearFindCourse = new TableColumn<>("School Year");

    @FXML
    private void onSubmitSearchCourseList(ActionEvent actionEvent) {
        this.errorSearchCourseList.setTextFill(Color.color(1, 0, 0));
        if (choiceBoxPicker(this.quarterSCL.getValue(), this.errorSearchCourseList, "Quarter field is required.") &&
                choiceBoxPicker(this.schoolYearSCL.getValue(), this.errorSearchCourseList,
                        "School year field is required.")) {

            String message = this.jdbcQuery.queryDataToSearchCourseByQuarterAndSchoolYear(
                    this.schoolYearSCL.getValue(),
                    this.quarterSCL.getValue(), tableCourseFindCourse);
            if (message == null) {
                this.errorSearchStudent.setTextFill(Color.color(0, 0, 1));
                this.errorSearchStudent.setText("Searching course list in " + this.quarterSCL.getValue() + ' '
                        + this.schoolYearSCL.getValue() + ": ");
            } else {
                this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
                this.errorSearchStudent.setText("Error Code: " + message);
            }
            this.clear();
        }

    }

    // Find Students by GPA
    @FXML
    public Label sliderLabel;
    @FXML
    private Label errorFindStudentGpa;
    @FXML
    private Slider findStudentGpaSlider = new Slider(0, 4, 0);
    @FXML
    private ChoiceBox<String> findStudentGpaChoiceBox = new ChoiceBox<>();
    @FXML
    private TableView<Table> findStudentGpaTable = new TableView<>();
    @FXML
    private final TableColumn<Table, String> fsgtColumn1 = new TableColumn<>("Student ID");
    @FXML
    private final TableColumn<Table, String> fsgtColumn2 = new TableColumn<>("Fist Name");
    @FXML
    private final TableColumn<Table, String> fsgtColumn3 = new TableColumn<>("Last Name");
    @FXML
    private final TableColumn<Table, String> fsgtColumn4 = new TableColumn<>("Major");
    @FXML
    private final TableColumn<Table, String> fsgtColumn5 = new TableColumn<>("GPA");

    @FXML
    private void onSubmitFindStudentGpa(ActionEvent actionEvent) {
        String message = this.jdbcQuery.queryDataToFindStudentsByGpaAndMajor(
                this.findStudentGpaSlider.getValue(),
                this.findStudentGpaChoiceBox.getValue(),
                this.findStudentGpaTable);

        if (message == null) {
            this.errorFindStudentGpa.setTextFill(Color.color(0, 0, 1));
            this.errorFindStudentGpa
                    .setText("Searching student with GPA higher than: " + this.findStudentGpaSlider.getValue());
        } else {
            this.errorFindStudentGpa.setTextFill(Color.color(1, 0, 0));
            this.errorFindStudentGpa.setText("Error Code: " + message);
        }
        this.clear();

    }

    // Find Professors by Class GPA
    @FXML
    public Label sliderLabel2;
    @FXML
    private Label errorFindProfessorGpa;
    @FXML
    private Slider findProfessorGpaSlider = new Slider(0, 4, 0);
    @FXML
    private ChoiceBox<String> findProfessorGpaChoiceBox = new ChoiceBox<>();
    @FXML
    private TableView<Table> findProfessorGpaTable = new TableView<>();
    @FXML
    private final TableColumn<Table, String> fpbgColumn1 = new TableColumn<>("Professor ID");
    @FXML
    private final TableColumn<Table, String> fpbgColumn2 = new TableColumn<>("Fist Name");
    @FXML
    private final TableColumn<Table, String> fpbgColumn3 = new TableColumn<>("Last Name");
    @FXML
    private final TableColumn<Table, String> fpbgColumn4 = new TableColumn<>("Department");
    @FXML
    private final TableColumn<Table, String> fpbgColumn5 = new TableColumn<>("Avg Class GPA");

    @FXML
    private void onSubmitFindProfessorGpa(ActionEvent actionEvent) {
        String message = this.jdbcQuery.queryToFindProfessorsByClassGpaAndDepartment(
                this.findProfessorGpaSlider.getValue(),
                this.findProfessorGpaChoiceBox.getValue(),
                this.findProfessorGpaTable);

        if (message == null) {
            this.errorFindProfessorGpa.setTextFill(Color.color(0, 0, 1));
            this.errorFindProfessorGpa
                    .setText("Searching professor with class GPA higher than: "
                            + this.findProfessorGpaSlider.getValue());
        } else {
            this.errorFindProfessorGpa.setTextFill(Color.color(1, 0, 0));
            this.errorFindProfessorGpa.setText("Error Code: " + message);
        }
        this.clear();
    }

    // Remove student
    @FXML
    private Label errorRemoveStudent;
    @FXML
    private TextField studentIdRS;
    @FXML
    private TableView<Table> tableRemoveStudent = new TableView<>();
    @FXML
    private final TableColumn<Table, String> rsColumn1 = new TableColumn<>("Student ID");
    @FXML
    private final TableColumn<Table, String> rsColumn2 = new TableColumn<>("First Name");
    @FXML
    private final TableColumn<Table, String> rsColumn3 = new TableColumn<>("Last Name");
    @FXML
    private final TableColumn<Table, String> rsColumn4 = new TableColumn<>("Email");
    @FXML
    private final TableColumn<Table, String> rsColumn5 = new TableColumn<>("Day of Birth");
    @FXML
    private final TableColumn<Table, String> rsColumn6 = new TableColumn<>("Major");

    @FXML
    private void onSubmitRemoveStudent(ActionEvent actionEvent) {
        this.errorRemoveStudent.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.studentIdRS.getText(), this.errorRemoveStudent, "Student ID must contain numbers only.")) {
            String message = jbdcRemove.removeByStudentID(this.studentIdRS.getText());

            if (message.equals("1")) {
                this.errorRemoveStudent.setTextFill(Color.color(0, 0, 1));
                this.errorRemoveStudent.setText("Successfully remove student!");
            } else if (message.equals("0")) {
                this.errorRemoveStudent.setTextFill(Color.color(0, 0, 1));
                this.errorRemoveStudent.setText("Student Id is not in database.");
            } else {
                this.errorRemoveStudent.setTextFill(Color.color(1, 0, 0));
                this.errorRemoveStudent.setText("Error Code: " + message);
            }

            this.clear();
        }
        this.jdbcQuery.queryToTableRemoveStudent(this.tableRemoveStudent);

    }

    // Remove professor
    @FXML
    private Label errorRemoveProfessor;
    @FXML
    private TextField professorIdRP;
    @FXML
    private TableView<Table> tableRemoveProfessor;
    @FXML
    private final TableColumn<Table, String> rpColumn1 = new TableColumn<>("Professor ID");
    @FXML
    private final TableColumn<Table, String> rpColumn2 = new TableColumn<>("First Name");
    @FXML
    private final TableColumn<Table, String> rpColumn3 = new TableColumn<>("Last Name");
    @FXML
    private final TableColumn<Table, String> rpColumn4 = new TableColumn<>("Email");
    @FXML
    private final TableColumn<Table, String> rpColumn5 = new TableColumn<>("Day of Birth");
    @FXML
    private final TableColumn<Table, String> rpColumn6 = new TableColumn<>("Department");

    @FXML
    private void onSubmitRemoveProfessor(ActionEvent actionEvent) {
        this.errorRemoveProfessor.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.professorIdRP.getText(), this.errorRemoveProfessor,
                "Professor ID must contain numbers only.")) {

            String message = jbdcRemove.removeByProfessorID(this.professorIdRP.getText());
            if (message.equals("1")) {
                this.errorRemoveProfessor.setTextFill(Color.color(0, 0, 1));
                this.errorRemoveProfessor.setText("Successfully remove professor!");
            } else if (message.equals("0")) {
                this.errorRemoveProfessor.setTextFill(Color.color(0, 0, 1));
                this.errorRemoveProfessor.setText("Professor ID is not in database.");
            } else {
                this.errorRemoveProfessor.setTextFill(Color.color(1, 0, 0));
                this.errorRemoveProfessor.setText("Error Code: " + message);
            }
            this.clear();
        }
        this.jdbcQuery.queryToTableRemoveProfessor(this.tableRemoveProfessor);

    }

    // Remove course
    @FXML
    private Label errorRemoveCourse;
    @FXML
    private TextField courseIdRC;
    @FXML
    private TableView<Table> tableRemoveCourse;
    @FXML
    private final TableColumn<Table, String> rcColumn1 = new TableColumn<>("Course ID");
    @FXML
    private final TableColumn<Table, String> rcColumn2 = new TableColumn<>("Course Title");
    @FXML
    private final TableColumn<Table, String> rcColumn3 = new TableColumn<>("Course Name");
    @FXML
    private final TableColumn<Table, String> rcColumn4 = new TableColumn<>("Professor ID");
    @FXML
    private final TableColumn<Table, String> rcColumn5 = new TableColumn<>("Quarter");
    @FXML
    private final TableColumn<Table, String> rcColumn6 = new TableColumn<>("School Year");

    @FXML
    public void onSubmitRemoveCourse(ActionEvent actionEvent) {
        this.errorRemoveCourse.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.courseIdRC.getText(), this.errorRemoveCourse, "Course ID must contain numbers only.")) {
            String message = jbdcRemove.removeByCourseID(this.courseIdRC.getText());

            if (message.equals("1")) {
                this.errorRemoveCourse.setTextFill(Color.color(0, 0, 1));
                this.errorRemoveCourse.setText("Successfully delete course!");
            } else if (message.equals("0")) {
                this.errorRemoveCourse.setTextFill(Color.color(0, 0, 1));
                this.errorRemoveCourse.setText("Course ID is not in database");
            } else {
                this.errorRemoveCourse.setTextFill(Color.color(1, 0, 0));
                this.errorRemoveCourse.setText("Error Code: " + message);
            }
            this.clear();
        }
        this.jdbcQuery.queryToTableRemoveCourse(this.tableRemoveCourse);

    }

    // clear event buttons
    @FXML
    public void onClearAddStudent(ActionEvent actionEvent) {
        this.errorAddStudent.setText("");
        this.clear();
    }

    @FXML
    private void onClearAddProfessor(ActionEvent actionEvent) {
        this.errorAddProfessor.setText("");
        this.clear();
    }

    @FXML
    private void onClearAddCourse(ActionEvent actionEvent) {
        this.errorAddCourse.setText("");
        this.clear();
    }

    @FXML
    private void onClearAddGrade(ActionEvent actionEvent) {
        this.errorAddGrade.setText("");
        this.clear();
    }

    @FXML
    private void onClearSearchStudent(ActionEvent actionEvent) {
        this.errorSearchStudent.setText("");
        this.clear();
    }

    @FXML
    private void onClearSearchProfessor(ActionEvent actionEvent) {
        this.errorSearchProfessor.setText("");
        this.clear();
    }

    @FXML
    private void onClearSearchStudentsInClass(ActionEvent actionEvent) {
        this.errorSearchStudentsInClass.setText("");
        this.clear();
    }

    @FXML
    private void onClearSearchCourseList(ActionEvent actionEvent) {
        this.errorSearchCourseList.setText("");
        this.clear();
    }

    @FXML
    private void onClearRemoveStudent(ActionEvent actionEvent) {
        this.errorRemoveStudent.setText("");
        this.clear();
    }

    @FXML
    private void onClearRemoveCourse(ActionEvent actionEvent) {
        this.errorRemoveCourse.setText("");
        this.clear();
    }

    @FXML
    private void onClearRemoveProfessor(ActionEvent actionEvent) {
        this.errorRemoveProfessor.setText("");
        this.clear();
    }

    @FXML
    private void onClearFindStudentGpa(ActionEvent actionEvent) {
        this.errorFindStudentGpa.setText("");
        this.clear();
    }

    @FXML
    private void onClearFindProfessorGpa(ActionEvent actionEvent) {
        this.errorFindProfessorGpa.setText("");
        this.clear();
    }

    private void clear() {
        this.firstNameAS.setText("");
        this.lastNameAS.setText("");
        this.emailAS.setText("");
        this.dobAS.setValue(null);
        this.majorAS.setValue(null);

        this.firstNameAP.setText("");
        this.lastNameAP.setText("");
        this.emailAP.setText("");
        this.dobAP.setValue(null);

        this.courseLabelAC.setText("");
        this.courseNameAC.setText("");
        this.instructorIdAC.setText("");
        this.quarterAC.setValue(null);
        this.schoolyearAC.setValue(null);

        this.courseIDAG.setText("");
        this.studentIDAG.setText("");
        this.gradeAG.setValue(null);

        this.studentIdSS.setText("");

        this.professorIdSP.setText("");

        this.courseIDSSC.setText("");

        this.quarterSCL.setValue(null);
        this.schoolYearSCL.setValue(null);

        this.studentIdRS.setText("");

        this.courseIdRC.setText("");

        this.professorIdRP.setText("");
    }

    public static boolean choiceBoxPicker(String choiceBox, Label warning, String message) {
        if (choiceBox == null) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

    public static boolean datePickerValidator(LocalDate date, Label warning, String message) {
        if (date == null) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

    public static boolean emailValidator(String email, Label warning, String message) {
        if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

    public static boolean idValidator(String id, Label warning, String message) {
        if (!id.matches("^[0-9]{1,}$")) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

    public static boolean nameValidator(String name, Label warning, String message) {

        if (!(name.length() >= 3)) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

}