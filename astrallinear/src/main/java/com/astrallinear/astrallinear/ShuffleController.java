package com.astrallinear.astrallinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShuffleController {

    @FXML
    private Button BackToGameButton;

    @FXML
    private GridPane CardShuffleGridPane;

    @FXML
    private Button ShuffleButton;

    @FXML
    void OnBackToGameButton(ActionEvent event) {
        //masukin method sebelum tutup windownya wajib di atas stage.close

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnShuffleButton(ActionEvent event) {
        //method buat shuffle
    }

}

