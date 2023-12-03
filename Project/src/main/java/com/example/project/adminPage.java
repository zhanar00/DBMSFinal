package com.example.project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class adminPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField Password1;

    @FXML
    private TextField Username;

    @FXML
    private Button adminPageLogin;

    @FXML
    private ImageView image;

    @FXML
    private Label text;

    @FXML
    void initialize() {
        adminPageLogin.setOnAction(actionEvent -> {
            String loginText = Username.getText();
            String passWordText = Password1.getText();
            File file1 = new File(loginText + "-" + passWordText + ".txt");
            if (file1.exists()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("newsPage.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Username or password is not correct");
                a.showAndWait();
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("hello-view.fxml"));
                try {
                    fxmlLoader1.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = fxmlLoader1.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });
    }

}

