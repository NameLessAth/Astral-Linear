package com.astrallinear.astrallinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;

public class Player1FieldController{

    @FXML
    private Label CurrentPlayerLabel;

    @FXML
    private Button EnemyFieldButton;

    @FXML
    private Button LoadButton;

    @FXML
    private Button MyFieldButton;

    @FXML
    private Button NextButton;

    @FXML
    private Label Player1Gold;

    @FXML
    private Label Player2Gold;

    @FXML
    private Button PluginButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Button ShopButton;

    @FXML
    private Label turnCount;
    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private GridPane LadangGridPane;
    @FXML
    private GridPane DeckGridPane;
    @FXML
    private AnchorPane DeckIndicatorPane;
    @FXML
    private Label CardLeftLabel;
    @FXML
    void OnEnemyFieldButtonClick(ActionEvent e) throws IOException{
        //tanpa mengubah giliran

        //tombol next di scene ladang musuh akan mengeluarkan pesan error setelah menekan tombol ini
        root = FXMLLoader.load(getClass().getResource("View/player2field.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnLoadPluginButtonClick(ActionEvent e) throws IOException {
        System.out.println("Load Plugin");
        root = FXMLLoader.load(getClass().getResource("View/loadplugin.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnLoadStateButtonClick(ActionEvent e) throws IOException{
        System.out.println("Load State");
        root = FXMLLoader.load(getClass().getResource("View/loadstate.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnMyFieldButtonClick(ActionEvent event) {
        System.out.println("My Field");
        //Jika sekarang giliran pemain 2 (berada di ladang lawan)
        //Kembali ke ladang pemain 2

        //Jika sekarang giliran pemain 1 (sudah berada di ladang sendiri) tampilkan error ini
        Alert alreadyInMyField = new Alert(AlertType.ERROR);
        alreadyInMyField.setTitle("Sudah di ladang sendiri");
        alreadyInMyField.setHeaderText("Anda sudah berada di ladang sendiri!");
        alreadyInMyField.show();
    }

    @FXML
    void OnNextButtonClick(ActionEvent e) throws IOException {
        //ganti giliran ke pemain selanjutnya
        root = FXMLLoader.load(getClass().getResource("View/player2field.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnSaveStateButtonClick(ActionEvent e) throws IOException {
        System.out.println("Save State");
        root = FXMLLoader.load(getClass().getResource("View/savestate.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnShopButtonClick(ActionEvent e) throws IOException {
        System.out.println("Shop");
        root = FXMLLoader.load(getClass().getResource("View/shop.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void handleDragDetectIMG(MouseEvent event) {
        ImageView IMGSource = (ImageView) event.getSource();
        Dragboard db = IMGSource.startDragAndDrop(TransferMode.ANY);
        System.out.println(IMGSource.getImage().getUrl());
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(IMGSource.getImage());
        db.setContent(cb);

        event.consume();
    }

    @FXML
    void handleDragDoneIMG(DragEvent event) {
        if (event.getTransferMode() != null) {
            ImageView IMGSource = (ImageView) event.getSource();
            Image img = new Image(Main.class.getResourceAsStream("Placeholder/EmptyCell.png"));
            IMGSource.setImage(img);
        }
    }

    @FXML
    void handleIMGDragOver(DragEvent event) {
        if (event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    @FXML
    void handleIMGDrop(DragEvent event) {
        ImageView target = (ImageView) event.getSource();
        if (event.getDragboard().hasImage()) {
            target.setImage(event.getDragboard().getImage());
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }
}
