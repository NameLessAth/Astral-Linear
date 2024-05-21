package com.astrallinear.astrallinear;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class SplashScreenController {

    @FXML
    private Scene scene;
    private Stage stage;
    private Parent root;
    @FXML
    protected void OnPlayButtonClick(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("View/player1field.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}