package com.example.project;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Email;

    @FXML
    private RadioButton Female;

    @FXML
    private Button Login;

    @FXML
    private RadioButton Male;

    @FXML
    private RadioButton Other;

    @FXML
    private PasswordField Password1;

    @FXML
    private Button SignUp;

    @FXML
    private TextField Username;

    @FXML
    private ToggleGroup gender;

    @FXML
    private ImageView image;

    @FXML
    private Label text;

    @FXML
    private Button backButton;

    ArrayList<String> array = new ArrayList<>();


    private final String folderName = "UserFiles";
    private final String fileName = "file.txt";


    @FXML
    void initialize() throws IOException {
        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
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
        createFolder();
        //logic();
    }

    void createFolder() {
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
        SignUp.setOnAction(actionEvent -> {
            String loginText = Username.getText();
            String passWordText = Password1.getText();
            String genderText = gender.toString();
            String emailText = Email.getText();


            try {
                logic();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                File f1 = new File(loginText + "-" + passWordText + ".txt");
                if (loginText.equals(passWordText) || passWordText.length() > 9 || emailText.length() > 9) {
                    CheckData();
                } else {
                    if (f1.createNewFile()) {
                        try (FileWriter myWriter = new FileWriter(f1)) {
                            myWriter.write("Username: " + loginText + "\nGmail: " + emailText + "\nPassword: " + passWordText +
                                    "\nGender: " + genderText);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Alert b = new Alert(Alert.AlertType.INFORMATION);
                        b.setTitle("Signed");
                        b.setContentText("Signed successful");
                        b.showAndWait();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
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
                        readFile();
                    }
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        });
    }

    void readFile() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("User is already exist");
        alert.showAndWait();
        try {
            throw new FileNotFoundException(null);
        } catch (FileNotFoundException e) {
            System.out.println("File exists");
        } catch (IOException e) {
            System.out.println("File Created");
        }
    }

    void addData(String username, String password, String email) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile(folderName + "/" + fileName, "rw");
            randomAccess.seek(randomAccess.length()); // Move the file pointer to the end of the file
            DataInput input = randomAccess;
            DataOutput output = randomAccess;
            output.writeBytes("Username :" + username + "\n");
            output.writeBytes("Email :" + email + "\n");
            output.writeBytes("Password :" + password + "\n");

            String line = input.readLine();
            System.out.println(line);
            randomAccess.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    void addData() throws IOException {
//        String loginText = Username.getText();
//        String passWordText = Password1.getText();
//        String emailText = Email.getText();
//
//        FileWriter fw = new FileWriter(folderName + "/" + fileName);
//        array.add("Username :" + loginText + "\n" + "Email :" + emailText + "\n" + "Password :" + passWordText + "\n");
//        for(String s : array){
//            fw.write(s +"\n");
//        }
//        fw.close();
//
//    }

    void CheckData() throws FileNotFoundException, IOException {
        Alert b = new Alert(Alert.AlertType.ERROR);
        b.setTitle("Password Matched");
        b.setContentText("Password Matched");
        b.showAndWait();
    }

    void countLines() {
        try (BufferedReader br = new BufferedReader(new FileReader(folderName + "/" + fileName))) {
            int lines = 0;
            while (br.readLine() != null)
                lines++;
            System.out.println("Number of lines: " + lines);
            Scanner scanner = new Scanner(folderName + "/" + fileName);
            if (scanner.hasNextLine()) {
                System.out.println("counting...");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logic() throws IOException {
        String loginText = Username.getText();
        String passWordText = Password1.getText();
        String emailText = Email.getText();

        File f1 = new File(loginText + "-" + passWordText + ".txt");
        if (!f1.exists()) {
            if (!loginText.equals(passWordText)) {
                addData(loginText, passWordText, emailText);
            } else {
                CheckData();
            }
        } else {
            readFile();
        }
        countLines();
    }
}


//try {
                //File f1 = new File(loginText + "-" + passWordText + ".txt");
                //if (f1.createNewFile()) {
//                    if(loginText.equals(passWordText) & loginText.length()>9 & passWordText.length()>9 & emailText.length()>9) {
//                        Alert b = new Alert(Alert.AlertType.ERROR);
//                        b.setTitle("Signed");
//                        b.setContentText("Password Matched");
//                        b.showAndWait();
//                    }
//                    else if(loginText==null & genderText==null & passWordText==null & emailText==null){
//                        Alert c = new Alert(Alert.AlertType.ERROR);
//                        c.setTitle("Empty!!!") ;
//                        c.setContentText("The fields are empty!");
//                        c.showAndWait();
//                    }
//                    else {
//                        try (FileWriter myWriter = new FileWriter(loginText + ".txt")) {
//                            myWriter.write("Username: " + loginText + "\nGmail: " + emailText + "\nPassword: " + passWordText +
//                                    "\nGender: " + genderText );
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Alert b = new Alert(Alert.AlertType.INFORMATION);
//                        b.setTitle("Signed");
//                        b.setContentText("Signed successful");
//                        b.showAndWait();
//                        FXMLLoader fxmlLoader = new FXMLLoader();
//                        fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
//                        try {
//                            fxmlLoader.load();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Parent root = fxmlLoader.getRoot();
//                        Stage stage = new Stage();
//                        stage.setScene(new Scene(root));
//                        stage.showAndWait();
//                    }
//                }
//            }catch (IOException e) {
//                System.out.println(e);
//            }
//        });
//    }
//}
