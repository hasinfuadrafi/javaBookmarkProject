package com.example.projectfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class LoginController {
    @FXML
    private Label loginPageLable;

    @FXML
    private PasswordField loginPagePassword;

    @FXML
    private Button loginPageSubmitButton;

    @FXML
    private TextField loginPageUserName;

    @FXML
    private Button done;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    FileWriter pass;

    {
        try {
            pass = new FileWriter("password.txt", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void usernamePasswordMatch(ActionEvent event) throws Exception{

        if(loginPageUserName.getText().equals("admin") && loginPagePassword.getText().equals("admin")){
            System.out.println("True");
            //Changing the scene to the starting scene
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartingScene.fxml"));
            Stage window = (Stage) loginPageSubmitButton.getScene().getWindow();
            window.setScene(new Scene(fxmlLoader.load()));


        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialogue");
            alert.setContentText("Incorrect username or password!!");
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }

    }

    @FXML
    void changePass(ActionEvent event)
    {



    }


}
