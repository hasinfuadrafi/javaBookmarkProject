package com.example.projectfiles;

import javafx.event.ActionEvent;
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
import java.util.Scanner;

public class HelloController {

    @FXML
    Button addNewEntryButton = new Button();
    @FXML
    Label newEntrylabel = new Label();

    @FXML
    TextField newEntryTitle = new TextField();

    @FXML
    TextField newEntryURL = new TextField();

    @FXML
    ComboBox newEntryComboBox = new ComboBox();

    @FXML
    TextField newEntryDescription = new TextField();

    @FXML
    Button newEntryFinishButton = new Button();

    @FXML
    Button test = new Button();

    public void newEntryDataWriting(ActionEvent event) throws Exception{

        File file = new File("newEntry.txt");

        try {

            FileWriter fileWriter = new FileWriter("newEntry.txt" , true);


            fileWriter.write(newEntryTitle.getText());
            fileWriter.write("\n");
            fileWriter.write(newEntryURL.getText());
            fileWriter.write("\n");
            fileWriter.write(newEntryDescription.getText());
            fileWriter.write("\n");

            fileWriter.close();



        }
        catch (Exception e){
            System.out.println("WARNING!!" + e.toString());
        }


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Switch.fxml"));
        Stage window = (Stage) newEntryFinishButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));



    }

    public void readCatagoryTextFile(){
        File file = new File("category.txt");

        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                newEntryComboBox.getItems().add(scanner.nextLine());
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public void addNewEntryButtonAction() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewEntry.fxml"));

        Stage window = (Stage) addNewEntryButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));

    }

    public void newEntryFinishButtonAction() throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Switch.fxml"));
        Stage window = (Stage) addNewEntryButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

}