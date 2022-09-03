package com.example.projectfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


//This class is the controller of "AddNewCatagoryEntryScene.fxml" file
public class AddCatagoyOrEntryClass {

    @FXML
    private Button addNewCatagoryButton;

    @FXML
    private Button addNewEntryButton;

    @FXML
    private Button backButton;


    public void addNewCatagoryButtonAction(ActionEvent event) throws Exception {


        //Changing the scene to the scene where we can add a new category and write it to the file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewCategory.fxml"));
        Stage window = (Stage) addNewCatagoryButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));

    }

    public void addNewEntryButtonAction(ActionEvent event) throws Exception {

        //Changing the scene to the scene where we can add a new Entry and write it to the file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewEntry.fxml"));
        Stage window = (Stage) addNewEntryButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));


    }

    public void backButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartingScene.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }


}
