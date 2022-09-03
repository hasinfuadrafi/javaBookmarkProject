package com.example.projectfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


//This class is the controller of "StartingScene.fxml" file


public class StartingSceneController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button startingSceneRefreshButton;

    @FXML
    private Button addButtonInStartingScene;
    @FXML
    private ListView<String> startingSceneListView;
    private String[] title;

    private String currentTitle;
    private String currentUrl;
    private String currentDescription;

    //private String deleteTheInformationUnderThisTitle;

    @FXML
    private Button logOut;

    @FXML
    private Button delete;

    //Has to be implemented for ListView to work properly
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showContentInListView();

        //Adding the array to the list
        startingSceneListView.getItems().addAll(title);


        //Click action definition
        startingSceneListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                currentTitle = startingSceneListView.getSelectionModel().getSelectedItem();
                String string = null;
                try {
                    readFiles();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                //This is the new window pop up
                //partially copied and not made using scenebuilder because couldn't figure out a way with scenebuilder
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);

                Button deleteButton = new Button("Delete");

                deleteButton.setOnAction(e -> {

                    try {

                        deleteButtonAction();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

                TextArea textField = new TextArea(currentUrl);
                TextArea textField1 = new TextArea(currentDescription);

                VBox layout = new VBox(textField, textField1, deleteButton);
                layout.setAlignment(Pos.CENTER);

                Scene scene = new Scene(layout, 300, 300);

                stage.setTitle("EntryInformation");
                stage.setScene(scene);
                stage.show();
                //New window popup end
            }
        });

    }

    //The purpose of this method is to initialize the title array so that it can be added to the listView
    public void showContentInListView() {

        File file = new File("newEntry.txt");

        //Setting up the scanner so that we can count the number of lines in the text file

        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Calculating the number of lines in the text file
        int numOfLines = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            numOfLines++;
        }

        //numOfLines/3 because we only want the title
        int temp = numOfLines / 3;
        scanner.reset();
        title = new String[temp];

        //Setting up new scanner so that we can read from the file
        Scanner scanner1 = null;
        try {
            scanner1 = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Reading the text file and adding only the title to the array because we want to display only the list
        //of titles
        for (int i = 0; i < temp; i++) {
            title[i] = scanner1.nextLine();
            scanner1.nextLine();
            scanner1.nextLine();

        }

    }

    //The purpose of this method is to initialize the variables currentURl and currentDescription so that they can be
    //viewed in the textArea
    public void readFiles() throws Exception {

        //File object for scanner
        File file = new File("newEntry.txt");
        Scanner scanner = new Scanner(file);

        //counting the number of lines
        int numOfLines = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            numOfLines++;
        }

        scanner.reset();

        //dividing by 3 because we have n number of lines and out of them n/3 number of lines are titles
        int temp = (numOfLines / 3);

        //New Scanner object so that we can read the file easily
        Scanner scanner1 = new Scanner(file);

        String[] titles = new String[temp];
        String[] URLs = new String[temp];
        String[] descriptions = new String[temp];

        //Initializing all the arrays with file information
        for (int i = 0; i < temp; i++) {
            titles[i] = scanner1.nextLine();
            URLs[i] = scanner1.nextLine();
            descriptions[i] = scanner1.nextLine();
        }

        //Finding the found index so we can initialize the URL and description variable for that index
        int foundIndex = -1;
        for (int i = 0; i < temp; i++) {
            if (currentTitle.equals(titles[i])) {
                foundIndex = i;
            }
        }

        if (foundIndex != -1) {
            currentUrl = URLs[foundIndex];
            currentDescription = descriptions[foundIndex];
        }
        //This else statement is only going to be called if the item has been deleted
        else {
            currentUrl = "Sorry!! You have deleted this file just now";
            currentDescription = "Sorry!! You have deleted this file just now";
        }
    }

    //This will delete the clicked title, description and the url
    public void deleteButtonAction() throws Exception {

        File file = new File("newEntry.txt");
        Scanner scanner = new Scanner(file);

        int numOfLines = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            numOfLines++;
        }

        scanner.reset();


        String[] allInformationInFile = new String[numOfLines];

        int temp = (numOfLines / 3);

        int foundIndex = 0;

        //IMPORTANT NOTE: loop is being controlled by temp *****
        for (int i = 0; i < temp; i++) {

            if (currentTitle.equals(title[i])) {

                //Multiplying by 3 to get the actual index of the array
                foundIndex = (i * 3);

            }
        }


        Scanner scanner2 = new Scanner(file);
        //Reading all the information in a single array using scanner2
        for (int i = 0; i < numOfLines; i++) {
            allInformationInFile[i] = scanner2.nextLine();
        }

        numOfLines = numOfLines - 3;
        for (int i = foundIndex; i < numOfLines; i++) {

            allInformationInFile[i] = allInformationInFile[i + 3];
            allInformationInFile[i + 1] = allInformationInFile[i + 3];
            allInformationInFile[i + 2] = allInformationInFile[i + 3];

        }



        System.out.println("Reached");
        FileWriter fileWriter = new FileWriter("newEntry.txt", false);

        for (int i = 0; i < numOfLines; i++) {
            fileWriter.write(allInformationInFile[i]);
            fileWriter.write("\n");
        }

        fileWriter.close();

    }


    //This Method implements what will happen if we click the add button on the Starting Scene

    public void addButtonInStartingSceneAction(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddNewCatagoryEntryScene.fxml"));
        Stage window = (Stage) addButtonInStartingScene.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));


    }


    public void startingSceneRefreshButtonAction(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartingScene.fxml"));
        Stage window = (Stage) addButtonInStartingScene.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    public void logOutAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Stage window = (Stage) logOut.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }



}