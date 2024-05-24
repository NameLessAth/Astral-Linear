package com.astrallinear.astrallinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainBeliController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane dynamicAnchorPane;
    @FXML
    private Button BackButton;
    //ganti data structure di sini jadi data barang yang disimpan di toko
    private List<String> data = List.of("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6","capek bang","java bosok","mending","unity");
    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    public void initialize() {
        try {
            populateAnchorPaneWithWidgets(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateAnchorPaneWithWidgets(List<String> data) throws IOException {
        double yOffset = 100.0;
        double initialXOffset = 200.0;
        double xOffset = initialXOffset;
        double widgetHeight = 250.0;
        double verticalSpacing = 20.0;
        double horizontalSpacing = 300.0;

        for (String item : data) {
            //spawn widget buat beli barang
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View/BeliWidget.fxml"));
            Node customWidget = loader.load();
            //dapetin controller dari semua widget
            BeliWidgetController controller = loader.getController();
            controller.getBuyItemNameLabel().setText(item); //buat ganti nama item
            //buat ganti gambar item, pake controller.getBuyItemImage().setImage(gambarnya)
            //buat ganti detail item, pake controller.getBuyItemDetailLabel().setImage(detail itemnya)
            //dibawah ini cuma buat setup posisi masing-masing widget
            AnchorPane.setTopAnchor(customWidget, yOffset);
            AnchorPane.setLeftAnchor(customWidget, xOffset);
            dynamicAnchorPane.getChildren().add(customWidget);
            xOffset += customWidget.getBoundsInLocal().getWidth() + horizontalSpacing;
            if (xOffset + customWidget.getBoundsInLocal().getWidth() > scrollPane.getWidth()) {
                xOffset = initialXOffset;
                yOffset += widgetHeight + verticalSpacing;
            }
        }
        dynamicAnchorPane.setPrefHeight(yOffset + widgetHeight + verticalSpacing);
        dynamicAnchorPane.setPrefWidth(1280);
    }

    @FXML
    void BackToShop(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("View/shop.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
