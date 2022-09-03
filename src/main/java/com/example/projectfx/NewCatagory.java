package com.example.projectfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;


//This is the controller class of "NewCategory.fxml" file
public class NewCatagory {

    @FXML
    private Button addButtonInAddNewCatagory;

    @FXML
    private TextField addCatagoryTextField;

    @FXML
    private Button backButton;

    @FXML
    private Label emptyTextfieldWarning;



    @FXML
    void addButtonInAddNewCatagoryAction(ActionEvent event) throws Exception {


        if(addCatagoryTextField.getText().isEmpty())
        {
            emptyTextfieldWarning.setText("*Textfield Can not be empty! ");
        }
        else {
            //Writing to the text file "category.txt"
            FileWriter fileWriter = new FileWriter("category.txt", true);
            fileWriter.write(addCatagoryTextField.getText());
            fileWriter.write("\n");
            fileWriter.close();

            //Changing the scene to the starting scene
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartingScene.fxml"));
            Stage window = (Stage) addButtonInAddNewCatagory.getScene().getWindow();
            window.setScene(new Scene(fxmlLoader.load()));
        }

    }

    @FXML
    void addButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddNewCatagoryEntryScene.fxml"));
        Stage window = (Stage) addButtonInAddNewCatagory.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

}






