package com.example.project;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class forgotPassword {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button saveButton;

    @FXML
    private TextField username;

    @FXML
    void initialize(){
        saveButton.setOnAction(actionEvent -> {
            String loginText = username.getText();
            String passWordText = password.getText();
            String emailText = email.getText();

            File file = new File("file.txt");
            String line = "";
            String eline = "";
            try {
                Scanner scan = new Scanner(file);
                while(scan.hasNextLine()){
                    line = line + loginText;
                    eline = eline + emailText;
                    if(loginText.equals(line) || emailText.equals(eline)){
                        FileWriter fw = new FileWriter("file.txt");
                        fw.write(passWordText);
                        fw.close();
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

//    public void updateFile(String s, String o, String n) throws IOException {
//        File file = new File("file.txt");
//        Scanner scan = new Scanner(file);
//
//        String newString = "";
//        while (scan.hasNextLine()) {
//            newString = newString + "\n" + scan.nextLine();
//        }
//        try (FileWriter fw = new FileWriter("file.txt")) {
//            newString = newString.replaceAll(o, n);
//            fw.write(newString);
//        }
//    }
}