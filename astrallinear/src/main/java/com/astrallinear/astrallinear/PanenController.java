package com.astrallinear.astrallinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PanenController {
    //Tolong baca teks yang ada di scene panen.fxml dulu buat cara displaynya
    @FXML
    private Button BackButton;

    @FXML
    private Label ItemDetailsLabel;

    @FXML
    private Button PanenButton;

    @FXML
    private ImageView PanenObjectIMG;

    @FXML
    private Label PanenObjectNameLabel;

    @FXML
    void OnBackButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnPanenButtonClick(ActionEvent event) {
        System.out.println("Panen button clicked");
    }
    @FXML
    public void initialize() throws IOException {

    }
}
