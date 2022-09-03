package com.example.projectfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


//This class is the controller of "NewEntry.fxml" file


public class NewEntryClass {

    @FXML
    private ComboBox newEntryComboBox;

    @FXML
    private TextField newEntryDescription;

    @FXML
    private Button newEntryFinishButton;

    @FXML
    private TextField newEntryTitle;

    @FXML
    private TextField newEntryURL;

    @FXML
    private Label newEntrylabel;

    @FXML
    private  Button backButton;

    @FXML
    private Label emptyTextfieldWarning;


    //This method is responsible for loading the combobox items just after the program starts
    //It does it by calling the readCategory() method
    @FXML
    public void initialize() {
        readCatagory();
    }

    //This method implements what will happen if we press the finish button

    @FXML
    public void newEntryDataWriting(ActionEvent event) throws Exception {


        //Writing in the text file using the file writer class
        FileWriter fileWriter = new FileWriter("newEntry.txt", true);

        //Getting all the information form the text fields
        if(newEntryTitle.getText().isEmpty() || newEntryURL.getText().isEmpty() || newEntryDescription.getText().isEmpty())
        {
           emptyTextfieldWarning.setText("*TextFields can't be empty! ");
        }
        else {
            fileWriter.write(newEntryTitle.getText());
            fileWriter.write("\n");
            fileWriter.write(newEntryURL.getText());
            fileWriter.write("\n");
            fileWriter.write(newEntryDescription.getText());
            fileWriter.write("\n");

            //Closing the file writer so that the information gets written
            fileWriter.close();


            //Changing the scene to the starting scene once all the information was written to the text file
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartingScene.fxml"));
            Stage window = (Stage) newEntryFinishButton.getScene().getWindow();
            window.setScene(new Scene(fxmlLoader.load()));
        }

    }


    //Responsible for reading the information from the "category.txt" file
    public void readCatagory() {

        File file = new File("category.txt");

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                newEntryComboBox.getItems().add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void backButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddNewCatagoryEntryScene.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));

    }


}




