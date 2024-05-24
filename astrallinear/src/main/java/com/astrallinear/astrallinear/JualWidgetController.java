package com.astrallinear.astrallinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class JualWidgetController {

    @FXML
    private Button SellButton;

    @FXML
    private Label SellItemDetailLabel;

    @FXML
    private ImageView SellItemImage;

    @FXML
    private Label SellItemNameLabel;

    @FXML
    void OnSellButtonClicked(ActionEvent event) {
        System.out.println("Jual: "+SellItemNameLabel.getText());
    }
    public Label getSellItemDetailLabel() {
        return SellItemDetailLabel;
    }
    public ImageView getSellItemImage() {
        return SellItemImage;
    }
    public Label getSellItemNameLabel() {
        return SellItemNameLabel;
    }
}
