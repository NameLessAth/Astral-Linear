package com.astrallinear.astrallinear;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
public class BeliWidgetController {

    @FXML
    private Button BuyButton;

    @FXML
    private Label BuyItemDetailLabel;

    @FXML
    private ImageView BuyItemImage;

    @FXML
    private Label BuyItemNameLabel;

    public Label getBuyItemDetailLabel() {
        return BuyItemDetailLabel;
    }
    public ImageView getBuyItemImage() {
        return BuyItemImage;
    }
    public Label getBuyItemNameLabel() {
        return BuyItemNameLabel;
    }
    @FXML
    void OnBuyButtonClicked(ActionEvent event) {
        System.out.println("Beli: "+BuyItemNameLabel.getText());
    }
}
