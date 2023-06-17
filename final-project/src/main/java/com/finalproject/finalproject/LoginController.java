package com.finalproject.finalproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    @FXML
    private void actionExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    private void actionLogin(ActionEvent actionEvent) throws IOException {
        Window owner = txtUsername.getScene().getWindow();

        if (txtUsername.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Username can not be empty", "Form error!");
            return;
        }
        if (txtPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Password can not be empty", "Form error!");
            return;
        }

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.equals("admin") && password.equals("admin")) {
            infoBox("Login successful!", null, "Success");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("POS | Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } else {
            infoBox("Authentication failed", null, "Failed");
        }

    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String message, String title) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.initOwner(owner);
        alert.showAndWait();
    }

}