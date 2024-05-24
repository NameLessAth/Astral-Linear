package com.astrallinear.astrallinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveStateSceneController {

    @FXML
    private Button BackButton;

    @FXML
    private ChoiceBox<String> FileFormatSelector;
    private String[] validFileFormats = {".txt",".json",".xml"};
    @FXML
    private TextField FileNameField;

    @FXML
    private Button SaveButton;
    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    void BackToGame(ActionEvent e) throws IOException {
        //kembali ke ladang pemaiin yang sekarang bermain
        root = FXMLLoader.load(getClass().getResource("View/player1field.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SaveState(ActionEvent event) {

        //nanti algoritma buat savenya taroh di sini
        System.out.println(FileFormatSelector.getValue());
        System.out.println(FileNameField.getText());
    }

    @FXML
    public void initialize(){
        FileFormatSelector.getItems().addAll(validFileFormats);
    }
}
