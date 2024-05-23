package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Deck.Deck;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Beruang.BearAttack;
import com.astrallinear.astrallinear.Beruang.*;
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

public class Player2FieldController{

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
    private static final String PLACEHOLDER_IMAGE_URL = "Placeholder/EmptyCell.png";
    private static GameManager gameManager;
    static {
        try {
            gameManager = GameManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnEnemyFieldButtonClick(ActionEvent e) throws IOException{
        //tanpa mengubah giliran
        Integer currentPlayer = gameManager.getCurrentPlayer();
        if(currentPlayer == 1){ //jika pemain sekarang adalah pemain 1 yang menekan tombol "Ladang Lawan" di ladang pemain 1
            //Dia sudah mengunjungi ladang lawan, keluarkan pesan error
            Alert alreadyInMyField = new Alert(AlertType.ERROR);
            alreadyInMyField.setTitle("Sudah di ladang lawan!");
            alreadyInMyField.setHeaderText("Pemain 1, kamu sudah berada di ladang lawan!");
            alreadyInMyField.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("View/player1field.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void OnLoadPluginButtonClick(ActionEvent e) throws IOException {
        System.out.println("Load Plugin");
        root = FXMLLoader.load(getClass().getResource("View/loadplugin.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(gameManager.getCurrentPlayer());
    }

    @FXML
    void OnLoadStateButtonClick(ActionEvent e) throws IOException{
        System.out.println("Load State");
        root = FXMLLoader.load(getClass().getResource("View/loadstate.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(gameManager.getCurrentPlayer());
    }

    @FXML
    void OnMyFieldButtonClick(ActionEvent e) throws IOException{
        //tanpa mengubah giliran
        Integer currentPlayer = gameManager.getCurrentPlayer();
        System.out.println(currentPlayer);
        if(currentPlayer == 2){
            //Jika sekarang giliran pemain 2 (sudah berada di ladang sendiri) tampilkan error ini
            Alert alreadyInMyField = new Alert(AlertType.ERROR);
            alreadyInMyField.setTitle("Sudah di ladang sendiri");
            alreadyInMyField.setHeaderText("Pemain 2, kamu sudah berada di ladangmu sendiri!");
            alreadyInMyField.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("View/player1field.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void OnNextButtonClick(ActionEvent e) throws Exception {
        Integer currentPlayer = gameManager.getCurrentPlayer();
        System.out.println(currentPlayer);
        if(currentPlayer == 1){
            Alert nextButtonAlert = new Alert(AlertType.ERROR);
            nextButtonAlert.setTitle("Next Button Error");
            nextButtonAlert.setHeaderText("Pemain 1, kamu masih di ladang lawan! Kembali ke ladangmu terlebih dahulu untuk lanjut ke turn berikutnya!");
            nextButtonAlert.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("View/player1field.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            gameManager.nextTurn();
            System.out.println(gameManager.getCurrentPlayer());
        }
    }

    @FXML
    void OnSaveStateButtonClick(ActionEvent e) throws IOException {
        System.out.println("Save State");
        root = FXMLLoader.load(getClass().getResource("View/savestate.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(gameManager.getCurrentPlayer());
    }
    @FXML
    void OnShopButtonClick(ActionEvent e) throws IOException {
        System.out.println("Shop");
        root = FXMLLoader.load(getClass().getResource("View/shop.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(gameManager.getCurrentPlayer());
    }
    @FXML
    void handleDragDetectIMG(MouseEvent event) {
        ImageView IMGSource = (ImageView) event.getSource();
        if (!isPlaceholderImage(IMGSource)) {
            Dragboard db = IMGSource.startDragAndDrop(TransferMode.ANY);
            ClipboardContent cb = new ClipboardContent();
            cb.putImage(IMGSource.getImage());
            db.setContent(cb);
        }
        event.consume();
    }

    @FXML
    void handleDragDoneIMG(DragEvent event) {
        if (event.getTransferMode() != null) {
            ImageView IMGSource = (ImageView) event.getSource();
            Image img = new Image(getClass().getResourceAsStream(PLACEHOLDER_IMAGE_URL));
            IMGSource.setImage(img);
            updateDraggableStatus(IMGSource);
        }
        event.consume();
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
        ImageView source = (ImageView) event.getGestureSource();
        if (event.getDragboard().hasImage() && target != source) {
            target.setImage(event.getDragboard().getImage());
            event.setDropCompleted(true);
            updateDraggableStatus(target);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }

    private boolean isPlaceholderImage(ImageView imageView) {
        Image image = imageView.getImage();
        if (image == null) {
            return false;
        }
        String imageUrl = image.getUrl();
        if (imageUrl == null) {
            return false;
        }
        String placeholderUrl = getClass().getResource(PLACEHOLDER_IMAGE_URL).toString();
        return imageUrl.equals(placeholderUrl);
    }

    private void updateDraggableStatus(ImageView imageView) {
        if (isPlaceholderImage(imageView)) {
            imageView.setOnDragDetected(null);
        } else {
            imageView.setOnDragDetected(this::handleDragDetectIMG);
        }
    }
}
