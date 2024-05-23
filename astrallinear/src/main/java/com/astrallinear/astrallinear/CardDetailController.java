package com.astrallinear.astrallinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CardDetailController {
    //tolong baca text yang muncul di carddetail.fxml terlebih dahulu buat display
    @FXML
    private Button BackButton;

    @FXML
    private ImageView CardIMG;

    @FXML
    private Label CardNameLabel;

    @FXML
    private Label ItemDetailsLabel;

    @FXML
    void OnBackButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    public void initialize() {

    }
}
