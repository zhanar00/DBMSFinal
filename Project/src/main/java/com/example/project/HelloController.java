package com.example.project;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class HelloController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label LblLogin;

    @FXML
    private Label LblPass;

    @FXML
    private Button FogotPassword;

    @FXML
    private Button Login;

    @FXML
    private PasswordField Password;

    @FXML
    private CheckBox RememberMe;

    @FXML
    private Button SignUp;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField Username;

    @FXML
    private ImageView image;

    @FXML
    private Label text;

    @FXML
    private Button AdminButton;

    ArrayList<String> Remembered = new ArrayList<>();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file2 = new File("Remembered.txt");
        if (file2.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file2));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Username.setText(line);
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Password.setText(line);
        }
        SignUp.setOnAction(actionEvent -> {
            SignUp.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("SingUp.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = fxmlLoader.getRoot();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        AdminButton.setOnAction(actionEvent -> {
            AdminButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("adminPage.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = fxmlLoader.getRoot();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        Login.setOnAction(actionEvent -> {
            if (!Username.getText().isEmpty() || !Password.getText().isEmpty()) {
                try {
                    LoginPage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                LblLogin.setText("Username is empty");
                LblPass.setText("Password is empty");
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The fields are empty \nPlease start over");
                a.showAndWait();
            }
        });

        FogotPassword.setOnAction(actionEvent -> {
            FogotPassword.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("fogotPass.fxml"));

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = fxmlLoader.getRoot();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    void LoginPage() throws IOException {
        String user = Username.getText();
        String passWordText = Password.getText();
        File file1 = new File(user + "-" + passWordText + ".txt");
        if (file1.exists()) {
            File file = new File("Remembered.txt");
            if (RememberMe.isSelected()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(user + System.lineSeparator() + passWordText);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try (FileWriter writer = new FileWriter(file, false)) {
                    writer.close();
                }
            }

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
        } else {
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
    }
}